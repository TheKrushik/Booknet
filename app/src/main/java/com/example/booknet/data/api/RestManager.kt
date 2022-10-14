package com.example.booknet.data.api

import android.content.Context
import com.example.booknet.BuildConfig
import com.example.booknet.data.prefs.SharedPrefsUser
import com.example.booknet.util.deviceId
import com.example.booknet.util.md5
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

const val APP = "app"
const val APP_VALUE = "android_booknet"
const val USER_TOKEN = "user_token"
const val DEVICE_ID = "device_id"
const val SIGN = "sign"
const val SECRET = "ca749658fabe44b08f2d56b2f4c054c0"
const val DEBUG = "debug"
const val DEBUG_VALUE = "1"

const val AUTHORIZATION = "Authorization"
const val AUTHORIZATION_VALUE = "Basic bGl0ZGV2OlBUaThleTYydGp5MFVlNg=="


fun getRestApi(ctx: Context, prefs: SharedPrefsUser): ApiService {
    return buildApi(buildRetrofit(buildClient(ctx, prefs)))
}

fun buildApi(retrofit: Retrofit): ApiService {
    return retrofit.create(ApiService::class.java)
}

fun buildRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

fun buildClient(ctx: Context, prefs: SharedPrefsUser): OkHttpClient {
    return OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .addInterceptor(AuthInterceptor(ctx, prefs))
        .addInterceptor(getHttpLoggingInterceptor())
        .build()
}

fun getHttpLoggingInterceptor(): HttpLoggingInterceptor {
    return HttpLoggingInterceptor().apply {
        level = if (BuildConfig.DEBUG)
            HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
    }
}

class AuthInterceptor(private val ctx: Context, private val prefs: SharedPrefsUser) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val userToken = prefs.token
        val deviceId = deviceId(ctx)
        val digitalHash = "$deviceId$SECRET$userToken"
        val sign = digitalHash.md5()
        val originRequest = chain.request()

        val url: HttpUrl = originRequest.url.newBuilder()
            .addQueryParameter(APP, APP_VALUE)
            .addQueryParameter(USER_TOKEN, userToken)
            .addQueryParameter(DEVICE_ID, deviceId)
            .addQueryParameter(SIGN, sign)
            .addQueryParameter(DEBUG, DEBUG_VALUE)
            .build()

        val request = userToken?.let {
            originRequest.newBuilder()
                .header(AUTHORIZATION, AUTHORIZATION_VALUE)
                .url(url)
        } ?: originRequest.newBuilder()

        return chain.proceed(request.build())
    }
}