package com.example.filmslibrary.ui.recyclerViewAdapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.filmslibrary.BuildConfig
import com.example.filmslibrary.R
import com.example.filmslibrary.application.App
import com.example.filmslibrary.databinding.FilmCardMaketBinding
import com.example.filmslibrary.model.mapper.FilmDtoMapper
import com.example.filmslibrary.model.repository.FilmObject
import com.example.filmslibrary.room.service.CacheFilmService
import com.example.filmslibrary.room.service.CacheFilmServiceImpl
import com.example.filmslibrary.room.service.HistoryService
import com.squareup.picasso.Picasso
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class ActualFilmsAdapter : RecyclerView.Adapter<ActualFilmsAdapter.ActualFilmsHolder>() {

    private var filmData: List<FilmObject> = listOf()
    var filmClickListener: FilmClickListener? = null
    private var cacheFilmService: CacheFilmService =
        CacheFilmServiceImpl(App.getCacheDao(), App.getFavoriteFilmDao())
    private val imageSourcePath: String = BuildConfig.IMAGE_SOURCE_PATH


    @SuppressLint("NotifyDataSetChanged")
    fun setFilm(films: List<FilmObject>) {
        filmData = films
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActualFilmsHolder {
        val binding = FilmCardMaketBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ActualFilmsHolder(binding.root, binding)
    }

    override fun getItemCount(): Int {
        return filmData.size
    }


    override fun onBindViewHolder(holder: ActualFilmsHolder, position: Int) {
        holder.bind(filmData[position], holder.getBinding())
    }

    inner class ActualFilmsHolder(itemView: View, binding: FilmCardMaketBinding) :
        RecyclerView.ViewHolder(itemView) {

        private var binding2 = binding

        fun bind(film: FilmObject, field: FilmCardMaketBinding) = with(field) {
            Picasso
                .get()
                .load(imageSourcePath + film.posterPath)
                .resizeDimen(R.dimen.film_cover_width, R.dimen.film_cover_height)
                .centerInside()
                .into(cover)

            title.text = film.title
            year.text = film.releaseDate

            if (film.mediaType == "movie") {
                mediaType.text = "??????????"
            } else mediaType.text = film.mediaType

            root.setOnClickListener {
                filmClickListener?.onFilmClicked(filmData[adapterPosition])
                val historyService = HistoryService(App.getHistoryDao())
                historyService.historyInsert(
                    FilmDtoMapper.filmObjectToHistoryEntity(
                        filmData[adapterPosition],
                        getDate()
                    )
                )
            }

            cacheFilmService.saveFilmToCache(FilmDtoMapper.filmObjectToFilmDto(film))
        }

        fun getBinding(): FilmCardMaketBinding {
            return binding2
        }

        private fun getDate(): String {
            val currentDate = Date()
            // ???????????????????????????? ?????????????? ?????? "????????.??????????.??????"
            val dateFormat: DateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
            val dateText: String = dateFormat.format(currentDate)
            // ???????????????????????????? ?????????????? ?????? "????????:????????????:??????????????"
            val timeFormat: DateFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
            val timeText: String = timeFormat.format(currentDate)
            return "???????? ??????????????: $dateText \n?????????? ??????????????: $timeText"
        }
    }

    fun interface FilmClickListener {
        fun onFilmClicked(film: FilmObject)
    }

    fun removeListener() {
        filmClickListener = null
    }
}