package com.example.booknet.presentation.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.booknet.data.api.ResultWrapper
import kotlinx.coroutines.launch

open class BaseViewModel : ViewModel() {

    private val _spinner = MutableLiveData<Boolean>(false)
    val spinner: LiveData<Boolean> get() = _spinner

    private val _noInternet = MutableLiveData<Boolean>(false)
    val noInternet: LiveData<Boolean> get() = _noInternet

    private val _snackBar = MutableLiveData<String?>()
    val snackbar: LiveData<String?> get() = _snackBar

    protected fun launchDataLoad(block: suspend () -> Unit): Unit {
        viewModelScope.launch {
            _spinner.value = true
            block()
            _spinner.value = false
        }
    }

    protected fun checkResult(result: ResultWrapper<Any>): Any {
        return when (result) {
            is ResultWrapper.NetworkError -> _noInternet.value = true
            is ResultWrapper.GenericError -> _snackBar.value =
                "httpCode=${result.code}, apiCode=${result.apiCode}, ${result.message}"
            is ResultWrapper.Success -> result.data
        }
    }

    internal fun onSnackbarHide() {
        _snackBar.value = null
    }

    internal fun onInternet() {
        _noInternet.value = false
    }
}