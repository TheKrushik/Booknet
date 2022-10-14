package com.example.booknet.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.booknet.data.api.ApiService
import com.example.booknet.data.api.ResultWrapper
import com.example.booknet.data.api.response.BooksResponse
import com.example.booknet.data.api.safeApiCall
import com.example.booknet.data.db.AppDatabase
import com.example.booknet.data.db.entity.BookEntity
import com.example.booknet.data.mapers.toBooks
import com.example.booknet.data.mapers.toListBook
import com.example.booknet.data.mapers.toListEntity
import com.example.booknet.domain.model.Book
import com.example.booknet.domain.repository.BooksRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class BooksRepositoryImpl(
    private val db: AppDatabase,
    private val rest: ApiService,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : BooksRepository {

    override fun getBooks(): LiveData<List<Book>> {
        return db.booksDao().booksLiveData()
            .map {
                it.toListBook()
            }
    }

    override suspend fun loadBooks(): ResultWrapper<List<Book>> {
        return safeApiCall(dispatcher) {
            val listResponse: List<BooksResponse> = rest.loadBooks()
            val list: List<Book> = listResponse.toBooks()
            val listDb: List<BookEntity> = list.toListEntity()
            db.booksDao().updateBooks(listDb)
            list
        }
    }
}