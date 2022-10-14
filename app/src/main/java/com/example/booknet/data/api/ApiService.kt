package com.example.booknet.data.api

import com.example.booknet.data.api.response.BooksResponse
import com.example.booknet.domain.model.BookChapters
import retrofit2.http.*

interface ApiService {

    @GET("library/get")
    suspend fun loadBooks(): List<BooksResponse>

    @GET("book/get-text/{bookId}")
    suspend fun loadBookChapters(
        @Path(value = "bookId", encoded = true) bookId: Int,
    ): List<BookChapters>

}