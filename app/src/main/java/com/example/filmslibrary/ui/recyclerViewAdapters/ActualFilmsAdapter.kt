package com.example.filmslibrary.ui.recyclerViewAdapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.filmslibrary.databinding.FilmCardMaketBinding
import com.example.filmslibrary.model.repository.FilmObject
import com.squareup.picasso.Picasso

class ActualFilmsAdapter : RecyclerView.Adapter<ActualFilmsAdapter.ActualFilmsHolder>() {

    private var filmData: List<FilmObject> = listOf()
    var filmClickListener: FilmClickListener? = null


    @SuppressLint("NotifyDataSetChanged")
    fun setFilm(films: List<FilmObject>) {
        filmData = films
        notifyDataSetChanged()
    }

//    fun setOnFilmClickListener(filmClickListenerFromActualFragment: FilmClickListener) {
//        this.filmClickListener = filmClickListenerFromActualFragment
//    }

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
                .load("https://image.tmdb.org/t/p/w500/" + film.posterPath)
                .fit()
                .into(cover)

            title.text = film.title
            year.text = film.releaseDate

            if (film.mediaType == "movie") {
                mediaType.text = "Фильм"
            }else mediaType.text = film.mediaType

            root.setOnClickListener { filmClickListener?.onFilmClicked(filmData[adapterPosition]) }
        }

        fun getBinding(): FilmCardMaketBinding {
            return binding2
        }
    }

    fun interface FilmClickListener {
        fun onFilmClicked(film:FilmObject)
    }

    fun removeListener() {
        filmClickListener = null
    }
}