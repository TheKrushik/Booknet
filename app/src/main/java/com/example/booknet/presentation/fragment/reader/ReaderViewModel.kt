package com.example.booknet.presentation.fragment.reader

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.booknet.data.api.ResultWrapper
import com.example.booknet.domain.model.BookChapters
import com.example.booknet.domain.repository.ReaderRepository
import com.example.booknet.presentation.base.BaseViewModel
import com.example.booknet.util.logD

class ReaderViewModel(
    private val repository: ReaderRepository,
) : BaseViewModel() {

    private val _bookChapters = MutableLiveData<List<BookChapters>>()
    val bookChapters: LiveData<List<BookChapters>> get() = _bookChapters

    fun loadBookChapters(bookId: Int) = launchDataLoad {

        val result = repository.loadBookChapters(bookId)
        checkResult(result)
        if (result is ResultWrapper.Success) {
            val data = result.data
            logD(data.toString())
            _bookChapters.value = data
        }
    }
}