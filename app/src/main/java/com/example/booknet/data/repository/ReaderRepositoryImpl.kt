package com.example.booknet.data.repository

import com.example.booknet.data.api.ApiService
import com.example.booknet.data.api.ResultWrapper
import com.example.booknet.data.api.safeApiCall
import com.example.booknet.domain.model.BookChapters
import com.example.booknet.domain.repository.ReaderRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class ReaderRepositoryImpl(
    private val rest: ApiService,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ReaderRepository {

    override suspend fun loadBookChapters(bookId: Int): ResultWrapper<List<BookChapters>> {
        return safeApiCall(dispatcher) {
            val list = rest.loadBookChapters(bookId)
            list
        }
    }
}