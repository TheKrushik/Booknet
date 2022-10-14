package com.example.booknet.presentation.fragment.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.booknet.data.api.ResultWrapper
import com.example.booknet.domain.model.Book
import com.example.booknet.domain.repository.BooksRepository
import com.example.booknet.presentation.base.BaseViewModel
import com.example.booknet.presentation.fragment.list.adapter.TypeLib
import com.example.booknet.util.logD

class ListBookViewModel(
    private val repository: BooksRepository,
) : BaseViewModel() {

    private val _books = MutableLiveData<List<Book>>()
    val books: LiveData<List<Book>> get() = _books

    private val _read = MutableLiveData<List<Book>>()
    val read: LiveData<List<Book>> get() = _read

    private val _archive = MutableLiveData<List<Book>>()
    val archive: LiveData<List<Book>> get() = _archive

    private val _favorites = MutableLiveData<List<Book>>()
    val favorites: LiveData<List<Book>> get() = _favorites

    fun loadBooks() = launchDataLoad {
        val result = repository.loadBooks()
        checkResult(result)
        if (result is ResultWrapper.Success) {
            val data = result.data
            logD(data.toString())
            _books.value = data

            _read.value = data.filter { it.libInfoType == TypeLib.Read.value }
            _archive.value = data.filter { it.libInfoType == TypeLib.Archive.value }
            _favorites.value = data.filter { it.libInfoType == TypeLib.Favorites.value }
        }
    }

}