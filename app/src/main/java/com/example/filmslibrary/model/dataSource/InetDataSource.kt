package com.example.filmslibrary.model.dataSource

import com.example.filmslibrary.model.repository.FilmObject
import kotlinx.coroutines.Deferred

interface InetDataSource<T> {

    suspend fun getDataAsync(apiKey: String, language: String): List<FilmObject>
}