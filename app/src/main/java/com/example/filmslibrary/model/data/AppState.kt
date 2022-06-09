package com.example.filmslibrary.model.data


import com.example.filmslibrary.model.dto.FilmDto
import com.example.filmslibrary.model.repository.FilmObject
import com.example.filmslibrary.model.repository.FilmsList
import com.example.filmslibrary.room.entity.FavoriteFilmEntity
import com.example.filmslibrary.room.entity.HistoryEntity
import com.example.filmslibrary.room.repository.FavoriteFilmDao
import kotlinx.coroutines.Deferred

sealed class AppState {
    data class Success(val filmsData: List<FilmObject>) : AppState()
    data class Error(val error: Throwable) : AppState()
    data class Loading (val progress:Int?): AppState()

    data class HistorySuccess(val historyData:List<HistoryEntity>):AppState()

    data class FavoriteSuccess(val favoriteData: List<FavoriteFilmEntity>):AppState()

}
