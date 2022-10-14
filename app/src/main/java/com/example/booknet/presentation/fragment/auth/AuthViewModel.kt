package com.example.booknet.presentation.fragment.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.booknet.data.prefs.SharedPrefsUser
import com.example.booknet.presentation.base.BaseViewModel

class AuthViewModel(
    private val prefs: SharedPrefsUser
) : BaseViewModel() {

    private val _isLogin = MutableLiveData<Boolean>(prefs.login)
    val isLogin: LiveData<Boolean> get() = _isLogin

    fun login(value: Boolean) {
        prefs.login = value
        _isLogin.value = value
    }
}