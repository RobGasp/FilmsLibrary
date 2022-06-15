package com.example.filmslibrary.model.firebaseDb


data class FavouriteFilmFirebase(
    val id: Long = 0L,
    val cacheFilmId: Long = 0L,
    val isFavorite: Boolean = false
) {}