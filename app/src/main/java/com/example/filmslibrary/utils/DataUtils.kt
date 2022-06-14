package com.example.filmslibrary.utils

import com.example.filmslibrary.model.firebaseDb.FavouriteFilmFirebase
import com.example.filmslibrary.room.entity.FavoriteFilmEntity


fun favouriteFilmFirebaseToFavouriteFilmEntity(list: List<FavouriteFilmFirebase>): List<FavoriteFilmEntity> {
    return list.map { item ->
        FavoriteFilmEntity(
            item.id,
            item.cacheFilmId,
            item.isFavorite,
        )
    }
}