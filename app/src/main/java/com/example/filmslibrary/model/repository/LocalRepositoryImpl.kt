package com.example.filmslibrary.model.repository

import com.example.filmslibrary.room.entity.CacheFilmEntity
import com.example.filmslibrary.room.repository.CacheFilmDao

class LocalRepositoryImpl(private val localDataSource: CacheFilmDao) {
    fun getAllCacheFilm(): List<CacheFilmEntity> {
        return localDataSource.all()
    }

    fun saveEntity(cache: CacheFilmEntity) {
        return localDataSource.insert(cache)
    }
}