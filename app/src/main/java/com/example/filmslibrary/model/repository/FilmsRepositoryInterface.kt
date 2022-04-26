package com.example.filmslibrary.model.repository

import kotlinx.coroutines.Deferred

interface FilmsRepositoryInterface<T> {
    suspend fun getListOfFilmsFromInternetAsync(apiKey: String, language: String): Deferred<List<FilmObject>>
}