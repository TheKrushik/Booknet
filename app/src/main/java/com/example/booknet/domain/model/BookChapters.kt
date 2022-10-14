package com.example.booknet.domain.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class BookChapters(
    @SerializedName("id") val id: Int,
    @SerializedName("access") val access: Boolean?,
    @SerializedName("text") val text: String? = null,
) : Parcelable