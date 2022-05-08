package com.example.filmslibrary.room.repository

import androidx.room.*
import com.example.filmslibrary.room.entity.CacheFilmEntity

@Dao
interface CacheFilmDao {
    @Query("SELECT * FROM CacheFilmEntity")
    fun all(): List<CacheFilmEntity>

    //Скорее всего этот метод не будет использоваться
    @Query("SELECT * FROM CacheFilmEntity WHERE id=:id")
    fun getById(id: Long): CacheFilmEntity

    @Query("SELECT * FROM CacheFilmEntity WHERE id=:filmId")
    fun getCacheFilmByFilmId(filmId: Long): CacheFilmEntity

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(filmEntity: CacheFilmEntity)

    @Update
    fun update(filmEntity: CacheFilmEntity)

    @Delete
    fun delete(filmEntity: CacheFilmEntity)
}