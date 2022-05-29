package com.example.filmslibrary.room.service

import com.example.filmslibrary.model.dto.FilmDto

interface CacheFilmService {

    fun findFilmById(filmId: Long): FilmDto

    fun saveFilmToCache(filmDto: FilmDto)
}