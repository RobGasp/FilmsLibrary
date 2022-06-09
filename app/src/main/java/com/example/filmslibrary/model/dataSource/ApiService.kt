package com.example.filmslibrary.model.dataSource

import com.example.filmslibrary.model.repository.FilmObject
import com.example.filmslibrary.model.repository.FilmsList
import kotlinx.coroutines.Deferred
import retrofit2.http.*

interface ApiService {

    @GET("trending/movie/day")
    fun getListOfFilmsAsync(
        @Query("api_key") apiKey: String,
        @Query("language")language: String
    ): Deferred<FilmsList>

    @GET("movie/{id}")
    fun getSingleFilmAsync(
        @Path("id")id:Long,
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ):Deferred<FilmObject>
}