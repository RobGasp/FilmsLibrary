package com.example.filmslibrary.model.repository

import com.example.filmslibrary.model.dataSource.InetDataSource
import kotlinx.coroutines.Deferred

class FilmsRepository(private val dataSource: InetDataSource<FilmsList,FilmObject>) :
    FilmsRepositoryInterface<FilmsList,FilmObject> {

    override suspend fun getListOfFilmsFromInternetAsync(
        apiKey: String,
        language: String
    ): FilmsList {
        return dataSource.getDataAsync(apiKey, language)
    }

    override suspend fun getSingleFilmFromInternetAsync(
        id: Long,
        apiKey: String,
        language: String
    ): FilmObject {
        return dataSource.getFilmAsync(id,apiKey,language)
    }
}