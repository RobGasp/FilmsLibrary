package com.example.filmslibrary.ui

import com.example.filmslibrary.model.repository.FilmObject

interface FilmClickListener {
    fun filmClicked(film:FilmObject)
}