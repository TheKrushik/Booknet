package com.example.booknet.presentation.fragment.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.booknet.data.prefs.SharedPrefsUser
import com.example.booknet.presentation.base.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModel(
    private val prefsUser: SharedPrefsUser
) : BaseViewModel() {

    private val _startAuth = MutableLiveData<Boolean>(false)
    val startAuth: LiveData<Boolean> get() = _startAuth

    private val _startClient = MutableLiveData<Boolean>(false)
    val startClient: LiveData<Boolean> get() = _startClient


    fun navigateToAuthOrMain() {
        viewModelScope.launch {
            delay(2_000)
            when {
                isNotLoginAvailable() -> _startAuth.value = true
                else -> _startClient.value = true
            }
        }
    }

    private fun isNotLoginAvailable(): Boolean = prefsUser.login == false

}