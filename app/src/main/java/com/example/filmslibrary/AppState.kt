package com.example.filmslibrary

import com.example.filmslibrary.repository.FilmObject

sealed class AppState {
    data class Success(val filmsData: List<FilmObject>?) : AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}
