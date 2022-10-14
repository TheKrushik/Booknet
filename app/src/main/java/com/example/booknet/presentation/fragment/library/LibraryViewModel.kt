package com.example.booknet.presentation.fragment.library

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.booknet.presentation.base.BaseViewModel

class LibraryViewModel : BaseViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is library Fragment"
    }
    val text: LiveData<String> = _text
}