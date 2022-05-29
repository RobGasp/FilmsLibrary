package com.example.filmslibrary.room.service

import com.example.filmslibrary.room.entity.HistoryEntity
import com.example.filmslibrary.room.repository.CacheFilmDao
import com.example.filmslibrary.room.repository.HistoryDao

class HistoryService(private val historyDao: HistoryDao/*, private val cacheFilmDao: CacheFilmDao*/) {

    fun getAllCache():List<HistoryEntity> {
        return historyDao.all()
    }

    fun historyInsert(entity: HistoryEntity){
        historyDao.insert(entity)
    }
}