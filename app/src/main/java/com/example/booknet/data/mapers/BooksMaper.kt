package com.example.booknet.data.mapers

import com.example.booknet.data.api.response.BooksResponse
import com.example.booknet.data.db.entity.BookEntity
import com.example.booknet.domain.model.Book
import com.example.booknet.domain.model.Genre
import com.google.gson.Gson

fun BooksResponse.toBook() = Book(
    id = this.book?.id ?: 0,
    title = this.book?.title ?: "",
    authorId = this.book?.authorId ?: 0,
    authorName = this.book?.authorName ?: "",
    authorAvatarUrl = this.book?.authorAvatarUrl ?: "",
    genres = this.book?.genres ?: emptyList(),
    libInfoType = this.libInfo?.type ?: 0
)

fun List<BooksResponse>.toBooks(): List<Book> {
    return this.map {
        it.toBook()
    }
}

fun Book.toEntity() = BookEntity(
    id = this.id,
    title = this.title ?: "",
    authorId = this.authorId ?: 0,
    authorName = this.authorName ?: "",
    authorAvatarUrl = this.authorAvatarUrl ?: "",
    genres = Gson().toJson(this.genres),
    libInfoType = this.libInfoType
)

fun List<Book>.toListEntity(): List<BookEntity> {
    return this.map {
        it.toEntity()
    }
}

fun BookEntity.toBook() = Book(
    id = this.id,
    title = this.title,
    authorId = this.authorId,
    authorName = this.authorName,
    authorAvatarUrl = this.authorAvatarUrl,
    genres = Gson().fromJson<List<Genre>>(this.genres, Genre::class.java),
    libInfoType = this.libInfoType
)

fun List<BookEntity>.toListBook(): List<Book> {
    return this.map {
        it.toBook()
    }
}