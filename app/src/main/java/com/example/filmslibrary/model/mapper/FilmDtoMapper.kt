package com.example.filmslibrary.model.mapper

import com.example.filmslibrary.application.App
import com.example.filmslibrary.model.dto.FilmDto
import com.example.filmslibrary.model.dto.HistoryDto
import com.example.filmslibrary.model.repository.FilmObject
import com.example.filmslibrary.room.entity.CacheFilmEntity
import com.example.filmslibrary.room.entity.FavoriteFilmEntity
import com.example.filmslibrary.room.entity.HistoryEntity

class FilmDtoMapper {

    companion object {
        fun filmEntityToFilmDao(
            filmEntity: CacheFilmEntity,
            favoriteFilmEntity: FavoriteFilmEntity
        ): FilmDto {
            val filmDto = FilmDto()

            filmDto.filmId = filmEntity.filmId
            filmDto.posterPath = filmEntity.posterPath
            filmDto.title = filmEntity.title
            filmDto.releaseDate = filmEntity.releaseDate
            filmDto.mediaType = filmEntity.mediaType
            filmDto.voteAverage = filmEntity.voteAverage
            filmDto.overview = filmEntity.overview
            filmDto.adult = filmEntity.adult
            filmDto.isFavorite = favoriteFilmEntity.isFavorite

            return filmDto
        }

        fun filmDtoToFilmEntity(filmDto: FilmDto): CacheFilmEntity {

            return CacheFilmEntity(
                filmDto.filmId,
                filmDto.posterPath,
                filmDto.title,
                filmDto.releaseDate,
                filmDto.mediaType,
                filmDto.voteAverage,
                filmDto.overview,
                filmDto.adult
            )
        }

        fun filmDtoToFavoriteFilmEntity(filmDto: FilmDto): FavoriteFilmEntity {
            return FavoriteFilmEntity(
                0, filmDto.filmId, filmDto.isFavorite
            )
        }

        fun filmObjectToFilmDto(filmObject: FilmObject): FilmDto {
            val filmDto = FilmDto()

            filmDto.filmId = filmObject.id ?: -1L
            filmDto.posterPath = filmObject.posterPath ?: ""
            filmDto.title = filmObject.title ?: ""
            filmDto.releaseDate = filmObject.releaseDate ?: ""
            filmDto.mediaType = filmObject.mediaType ?: ""
            filmDto.voteAverage = filmObject.voteAverage ?: 0.0
            filmDto.overview = filmObject.overview ?: ""
            filmDto.adult = filmObject.adult ?: false
            filmDto.isFavorite = false

            return filmDto
        }

        fun filmObjectToHistoryDao(filmObject: FilmObject, dateRequest: String): HistoryEntity {
            val historyEntity = HistoryEntity(0L, 0L, "")

            historyEntity.id = filmObject.id
            historyEntity.cacheFilmId = filmObject.id
            historyEntity.dateRequest = dateRequest

            return historyEntity
        }

        fun historyDaoToFilmObject(historyEntity: HistoryEntity): FilmObject {

            val film = App.getCacheDao().getById(historyEntity.cacheFilmId)
            return FilmObject(
                film.filmId,
                film.posterPath,
                film.title,
                film.releaseDate,
                film.mediaType,
                film.voteAverage,
                film.overview,
                film.adult
            )
        }

    }
}