package com.example.booknet.data.api.response

import com.example.booknet.domain.model.Book
import com.google.gson.annotations.SerializedName

data class BooksResponse(
    @SerializedName("lib_info") val libInfo: LibInfo?,
    @SerializedName("book") val book: Book?
)

data class LibInfo(
    @SerializedName("add_date") val addDate: Long?,
    @SerializedName("last_chr_count") val lastChrCount: Int?,
    @SerializedName("type") val type: Int?,
)