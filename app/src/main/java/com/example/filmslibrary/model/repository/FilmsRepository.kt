package com.example.filmslibrary.model.repository

import com.example.filmslibrary.model.dataSource.InetDataSource
import kotlinx.coroutines.Deferred

class FilmsRepository (private val dataSource: InetDataSource<List<FilmObject>>) : FilmsRepositoryInterface<List<FilmObject>> {

    override suspend fun getListOfFilmsFromInternetAsync(apiKey: String, language: String): List<FilmObject> {
        return dataSource.getDataAsync(apiKey, language)
    }
}