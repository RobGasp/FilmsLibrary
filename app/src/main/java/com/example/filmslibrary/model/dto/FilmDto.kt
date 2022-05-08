package com.example.filmslibrary.model.dto

data class FilmDto (
    var filmId: Int = 0,
    var posterPath: String = "",
    var title: String = "",
    var releaseDate: String = "",
    var mediaType: String = "",
    var voteAverage: Double = 0.0,
    var overview: String = "",
    var adult: Boolean = false,
    var isFavorite: Boolean = false
)