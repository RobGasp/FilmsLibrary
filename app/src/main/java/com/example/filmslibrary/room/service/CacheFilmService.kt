package com.example.filmslibrary.room.service

import androidx.room.Transaction
import com.example.filmslibrary.model.dto.FilmDto
import com.example.filmslibrary.model.mapper.FilmDtoMapper
import com.example.filmslibrary.room.repository.CacheFilmDao
import com.example.filmslibrary.room.repository.FavoriteFilmDao

class CacheFilmService(private val cacheFilmDao: CacheFilmDao, private val favoriteFilmDao: FavoriteFilmDao) {

    @Transaction
    fun findFilmById(filmId: Long): FilmDto {
        val filmEntity = cacheFilmDao.getCacheFilmByFilmId(filmId)
        val favoriteFilmEntity = favoriteFilmDao.getFavoriteFilmByFilmId(filmId)

        return FilmDtoMapper.filmEntityToFilmDao(filmEntity, favoriteFilmEntity)
    }

    @Transaction
    fun saveFilmToCache(filmDto: FilmDto) {
        val filmEntity = FilmDtoMapper.filmDtoToFilmEntity(filmDto)
        val favoriteFilmEntity = FilmDtoMapper.filmDtoToFavoriteFilmEntity(filmDto)

        cacheFilmDao.insert(filmEntity)
        favoriteFilmDao.insert(favoriteFilmEntity)
    }
}