package com.example.booknet.presentation.fragment.contest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.booknet.presentation.base.BaseViewModel

class ContestViewModel : BaseViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is contest Fragment"
    }
    val text: LiveData<String> = _text
}