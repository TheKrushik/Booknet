package com.example.booknet.domain.repository

import com.example.booknet.data.api.ResultWrapper
import com.example.booknet.domain.model.BookChapters

interface ReaderRepository {

    suspend fun loadBookChapters(bookId: Int): ResultWrapper<List<BookChapters>>
}