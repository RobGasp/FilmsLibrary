package com.example.filmslibrary.room.service

import com.example.filmslibrary.model.repository.FilmObject
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

    fun getIsAdult(filmObject: FilmObject):FavoriteFilmEntity{
        return favoriteFilmDao.getFavoriteFilmByFilmId(filmObject.id)
    }

    fun getFavoriteFilm(id:Long):FavoriteFilmEntity{
        return favoriteFilmDao.getFavoriteFilmByFilmId(id)
    }
}