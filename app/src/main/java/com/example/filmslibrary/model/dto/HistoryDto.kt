package com.example.filmslibrary.model.dto

import java.time.LocalDateTime

data class HistoryDto (
    val date: LocalDateTime,
    val films: List<FilmDto>
    )