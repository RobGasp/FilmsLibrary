package com.example.filmslibrary.model.repository

import com.example.filmslibrary.model.dataSource.DataSource
import kotlinx.coroutines.Deferred

class FilmsRepository (private val dataSource: DataSource<List<FilmObject>>) : FilmsRepositoryInterface<List<FilmObject>> {

    override suspend fun getListOfFilmsFromInternetAsync(apiKey: String, language: String): Deferred<List<FilmObject>> {
        return dataSource.getDataAsync(apiKey, language)
    }
}