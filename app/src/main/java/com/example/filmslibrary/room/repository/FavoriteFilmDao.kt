package com.example.filmslibrary.room.repository

import androidx.room.*
import com.example.filmslibrary.room.entity.FavoriteFilmEntity

@Dao
interface FavoriteFilmDao {
    @Query("SELECT * FROM FavoriteFilmEntity")
    fun all(): List<FavoriteFilmEntity>

    @Query("SELECT * FROM FavoriteFilmEntity WHERE id=:id")
    fun getById(id: Long): FavoriteFilmEntity

    @Query("SELECT * FROM FavoriteFilmEntity WHERE id=:filmId")
    fun getFavoriteFilmByFilmId(filmId: Long): FavoriteFilmEntity

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(entity: FavoriteFilmEntity)

    @Update
    fun update(entity: FavoriteFilmEntity)

    @Delete
    fun delete(entity: FavoriteFilmEntity)
}