package com.example.filmslibrary.model.dataSource

import com.example.filmslibrary.model.repository.FilmObject
import com.example.filmslibrary.model.repository.FilmsList
import kotlinx.coroutines.Deferred

interface InetDataSource<T> {

    suspend fun getDataAsync(apiKey: String, language: String): FilmsList
}