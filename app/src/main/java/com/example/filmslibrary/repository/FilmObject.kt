package com.example.filmslibrary.repository

import android.os.Build
import android.os.Parcelable
import androidx.annotation.RequiresApi
import com.example.filmslibrary.MainActivity
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Exception
import java.lang.StringBuilder
import java.net.HttpURLConnection
import java.net.URL
import java.util.stream.Collectors
import javax.net.ssl.HttpsURLConnection

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