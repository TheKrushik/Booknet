package com.example.booknet.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "table_book", primaryKeys = ["id"])
data class BookEntity(

    @ColumnInfo(name = "id") var id: Int,
    @ColumnInfo(name = "title") var title: String,
    @ColumnInfo(name = "author_id") val authorId: Int,
    @ColumnInfo(name = "author_name") val authorName: String,
    @ColumnInfo(name = "author_avatar_url") val authorAvatarUrl: String,
    @ColumnInfo(name = "genres") val genres: String,

    @ColumnInfo(name = "libInfoType") val libInfoType: Int
)