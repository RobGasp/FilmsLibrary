package com.example.filmslibrary.model.dataSource

import com.example.filmslibrary.model.repository.FilmObject
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("trending/movie/day")
    fun getListOfFilmsAsync(
        @Query("day") apiKey: String,
        language: String
    ): Deferred<List<FilmObject>>
}