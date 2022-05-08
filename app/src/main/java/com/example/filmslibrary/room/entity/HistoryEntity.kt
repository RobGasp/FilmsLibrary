package com.example.filmslibrary.room.entity

import androidx.room.*

@Entity
data class HistoryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    @ColumnInfo(name = "cache_film_id")
    val cacheFilmId: Long,
    @ColumnInfo(name = "date_request")
    val dateRequest: Long
)
