package com.example.filmslibrary.model.repository

interface FilmsRepositoryInterface<T> {
    suspend fun getListOfFilmsFromInternetAsync(apiKey: String, language: String): FilmsList
}