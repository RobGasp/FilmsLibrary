package com.example.filmslibrary.model.data


import com.example.filmslibrary.model.dto.FilmDto
import com.example.filmslibrary.model.repository.FilmObject
import com.example.filmslibrary.model.repository.FilmsList
import com.example.filmslibrary.room.entity.HistoryEntity
import kotlinx.coroutines.Deferred

sealed class AppState {
    data class Success(val filmsData: List<FilmObject>) : AppState()
    data class Error(val error: Throwable) : AppState()
    data class Loading (val progress:Int?): AppState()

    data class HistorySuccess(val historyData:List<HistoryEntity>):AppState()

}
