package com.example.booknet.domain.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Genre(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String?,
    @SerializedName("rating_place") val ratingPlace: Int? = null
) : Parcelable