package com.example.filmslibrary.model.repository

interface FilmsRepositoryInterface<T,U> {
    suspend fun getListOfFilmsFromInternetAsync(apiKey: String, language: String): T
    suspend fun getSingleFilmFromInternetAsync(id:Long,apiKey: String, language: String): U
}
