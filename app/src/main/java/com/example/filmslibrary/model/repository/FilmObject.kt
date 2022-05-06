package com.example.filmslibrary.model.repository

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class FilmObject(
    var id: Int? = 0,
    @SerializedName("poster_path")
    var posterPath: String? = null,

    var title: String? = null,

    @SerializedName("release_date")
    var releaseDate: String? = null,

    @SerializedName("media_type")
    var mediaType: String? = null,

    @SerializedName("vote_average")
    var voteAverage: Double? = 0.0,
    var overview: String? = null,

    @SerializedName("adult")
    var adult: Boolean? = false,
) : Parcelable {

}