package com.example.filmslibrary.model

import com.example.filmslibrary.model.repository.FilmObject

sealed class AppState {
    data class Success(val filmsData: List<FilmObject>?) : AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}
