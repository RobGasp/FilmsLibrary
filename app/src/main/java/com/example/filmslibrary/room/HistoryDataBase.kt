package com.example.filmslibrary.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.filmslibrary.room.repository.CacheFilmDao
import com.example.filmslibrary.room.repository.HistoryDao
import com.example.filmslibrary.room.entity.CacheFilmEntity
import com.example.filmslibrary.room.entity.FavoriteFilmEntity
import com.example.filmslibrary.room.entity.HistoryEntity
import com.example.filmslibrary.room.repository.FavoriteFilmDao

@Database(entities = [HistoryEntity::class, CacheFilmEntity::class, FavoriteFilmEntity::class], version = 4, exportSchema = false)
abstract class FilmotekaDataBase : RoomDatabase() {
    abstract fun historyDao(): HistoryDao
    abstract fun cacheDao(): CacheFilmDao
    abstract fun favoriteDao(): FavoriteFilmDao
}