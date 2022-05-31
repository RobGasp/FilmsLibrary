package com.example.filmslibrary.room.entity

import androidx.room.*

@Entity
data class HistoryEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long,
    @ColumnInfo(name = "cache_film_id")
    var cacheFilmId: Long,
    @ColumnInfo(name = "date_request")
    var dateRequest: String
)
