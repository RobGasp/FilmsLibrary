package com.example.filmslibrary.repository

interface FilmsRepositoryInterface {
    fun getListOfFilmsFromInternet():List<FilmObject>?
}