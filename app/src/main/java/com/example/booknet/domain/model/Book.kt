package com.example.booknet.domain.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Book(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String?,
    @SerializedName("author_id") val authorId: Int? = null,
    @SerializedName("author_name") val authorName: String? = null,
    @SerializedName("author_avatar_url") val authorAvatarUrl: String? = null,
    @SerializedName("genres") val genres: List<Genre>? = null,

    @SerializedName("libInfoType") val libInfoType: Int = 0,
) : Parcelable