package com.example.booknet.domain.repository

import androidx.lifecycle.LiveData
import com.example.booknet.data.api.ResultWrapper
import com.example.booknet.domain.model.Book

interface BooksRepository {

    fun getBooks(): LiveData<List<Book>>
    suspend fun loadBooks(): ResultWrapper<List<Book>>
}