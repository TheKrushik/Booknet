package com.example.booknet.data.api

import com.example.booknet.util.logD
import com.squareup.moshi.Moshi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

sealed class ResultWrapper<out T> {
    data class Success<out T>(val data: T) : ResultWrapper<T>()
    data class GenericError(val code: Int?, val apiCode: Int?, val message: String?) :
        ResultWrapper<Nothing>()
    object NetworkError : ResultWrapper<Nothing>()
}

data class ErrorResponse(
    val error_code: Int,
    val error: String
)

suspend fun <T> safeApiCall(
    dispatcher: CoroutineDispatcher,
    apiCall: suspend () -> T
): ResultWrapper<T> {
    return withContext(dispatcher) {
        try {
            val data = apiCall.invoke()
            ResultWrapper.Success(data)
        } catch (throwable: Throwable) {
            when (throwable) {
                is IOException -> {
                    logD("IOException -> NetworkError")
                    ResultWrapper.NetworkError
                }
                is HttpException -> {
                    val code = throwable.code()
                    val errorResp = convertErrorBody(throwable)
                    val apiCode = errorResp?.error_code
                    val apiMessage = errorResp?.error
                    val message = "apiCode=$apiCode, apiMessage=$apiMessage"

                    logD("HttpException -> httpCode=${code}, $message")
                    ResultWrapper.GenericError(code, apiCode, apiMessage)
                }
                else -> {
                    val cause = throwable.cause
                    val message = throwable.message
                    val error = "cause=$cause, message=$message"

                    logD("else -> $error")
                    ResultWrapper.GenericError(null, null, error)
                }
            }
        }
    }
}

private fun convertErrorBody(throwable: HttpException): ErrorResponse? {
    return try {
        throwable.response()?.errorBody()?.source()?.let {
            logD(it.toString())
            val moshiAdapter = Moshi.Builder().build().adapter(ErrorResponse::class.java)
            moshiAdapter.fromJson(it)
        }
    } catch (exception: Exception) {
        null
    }
}