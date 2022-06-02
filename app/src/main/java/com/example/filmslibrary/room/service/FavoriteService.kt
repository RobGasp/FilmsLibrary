package com.example.filmslibrary.room.service

import com.example.filmslibrary.room.entity.FavoriteFilmEntity
import com.example.filmslibrary.room.repository.FavoriteFilmDao

class FavoriteService (private val favoriteFilmDao: FavoriteFilmDao) {

    fun getAllFavoriteFilms():List<FavoriteFilmEntity>{
        return favoriteFilmDao.all()
    }

    fun insertFavoriteFilm(favoriteFilmEntity: FavoriteFilmEntity){
        favoriteFilmDao.insert(favoriteFilmEntity)
    }

    fun deleteFavoriteFilm(favoriteFilmEntity: FavoriteFilmEntity){
        favoriteFilmDao.delete(favoriteFilmEntity)
    }
}