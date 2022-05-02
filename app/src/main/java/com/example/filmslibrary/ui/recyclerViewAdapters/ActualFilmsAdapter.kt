package com.example.filmslibrary.ui.recyclerViewAdapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.filmslibrary.databinding.FilmCardMaketBinding
import com.example.filmslibrary.model.repository.FilmObject
import com.example.filmslibrary.ui.FilmClickListener
import com.squareup.picasso.Picasso

class ActualFilmsAdapter : RecyclerView.Adapter<ActualFilmsAdapter.ActualFilmsHolder>() {

    private var filmData: List<FilmObject> = listOf()
    private var filmClickListener: FilmClickListener? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setFilm(films: List<FilmObject>) {
        filmData = films
        notifyDataSetChanged()
    }

    fun setOnFilmClickListener(filmClickListenerFromActualFragment: FilmClickListener, film: FilmObject){
        this.filmClickListener = filmClickListenerFromActualFragment
        filmClickListener?.filmClicked(film)
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
            get() = field

        fun bind(film: FilmObject, field: FilmCardMaketBinding) = with(field) {
            Picasso
                .get()
                .load(film.posterPath)
                .fit()
                .into(cover)

            title.text = film.title
            year.text = film.releaseDate
            genre.text = film.mediaType
        }

        fun getBinding(): FilmCardMaketBinding {
            return binding2
        }
    }
}