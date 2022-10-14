package com.example.booknet.presentation.fragment.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.booknet.presentation.base.BaseViewModel

class SettingsViewModel : BaseViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is settings Fragment"
    }
    val text: LiveData<String> = _text
}