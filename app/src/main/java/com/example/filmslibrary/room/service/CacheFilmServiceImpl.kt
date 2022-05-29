package com.example.filmslibrary.room.service

import androidx.room.Transaction
import com.example.filmslibrary.model.dto.FilmDto
import com.example.filmslibrary.model.mapper.FilmDtoMapper
import com.example.filmslibrary.room.repository.CacheFilmDao
import com.example.filmslibrary.room.repository.FavoriteFilmDao

class CacheFilmServiceImpl(private val cacheFilmDao: CacheFilmDao, private val favoriteFilmDao: FavoriteFilmDao):CacheFilmService {

    @Transaction
    override fun findFilmById(filmId: Long): FilmDto {
        val filmEntity = cacheFilmDao.getCacheFilmByFilmId(filmId)
        val favoriteFilmEntity = favoriteFilmDao.getFavoriteFilmByFilmId(filmId)

        return FilmDtoMapper.filmEntityToFilmDao(filmEntity, favoriteFilmEntity)
    }

    @Transaction
    override fun saveFilmToCache(filmDto: FilmDto) {
        val filmEntity = FilmDtoMapper.filmDtoToFilmEntity(filmDto)

        cacheFilmDao.insert(filmEntity)
    }

    @Transaction
    fun saveFilmToFavorite(filmDto: FilmDto){
        val favoriteFilmEntity = FilmDtoMapper.filmDtoToFavoriteFilmEntity(filmDto)
        favoriteFilmDao.insert(favoriteFilmEntity)
    }
}