package com.example.filmslibrary.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class FavoriteFilmEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    @ColumnInfo(name = "favorite_film_id")
    val cacheFilmId: Long,
    @ColumnInfo(name = "is_favorite")
    val isFavorite: Boolean
)