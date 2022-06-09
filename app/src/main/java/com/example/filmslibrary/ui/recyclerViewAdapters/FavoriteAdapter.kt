package com.example.filmslibrary.ui.recyclerViewAdapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.filmslibrary.databinding.FavoriteCardMaketBinding
import com.example.filmslibrary.model.repository.FilmObject
import com.example.filmslibrary.model.repository.FilmsList
import com.example.filmslibrary.model.repository.FilmsRepository
import com.example.filmslibrary.model.repository.FilmsRepositoryInterface
import com.example.filmslibrary.room.entity.FavoriteFilmEntity
import com.squareup.picasso.Picasso
import kotlinx.coroutines.*

class FavoriteAdapter : RecyclerView.Adapter<FavoriteAdapter.FavoriteHolder>(),
    CoroutineScope by MainScope() {

    var favoriteClickListener: FavoriteClickListener? = null
    private var favoriteData: List<FavoriteFilmEntity> = listOf()
    private var filmsRepositoryInterface: FilmsRepositoryInterface<FilmsList, FilmObject>? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setFavorite(favoriteFilmEntity: List<FavoriteFilmEntity>) {
        favoriteData = favoriteFilmEntity
        notifyDataSetChanged()
    }

    fun setFilmsRepositoryInterface(filmsRepositoryInt: FilmsRepositoryInterface<FilmsList, FilmObject>) {
        filmsRepositoryInterface = filmsRepositoryInt
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteHolder {
        val binding =
            FavoriteCardMaketBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteHolder(binding.root, binding)
    }

    override fun onBindViewHolder(holder: FavoriteHolder, position: Int) {
        holder.bind(favoriteData[position], holder.getBinding())
    }

    override fun getItemCount(): Int {
        return favoriteData.size
    }

    inner class FavoriteHolder(itemView: View, binding: FavoriteCardMaketBinding) :
        RecyclerView.ViewHolder(itemView) {

        private var binding2 = binding

        fun bind(favoriteSingleFilm: FavoriteFilmEntity, field: FavoriteCardMaketBinding) =
            with(field) {
                // var movie: FilmObject?=FilmObject()
                launch {
                    val movie = filmsRepositoryInterface?.getSingleFilmFromInternetAsync(
                        favoriteSingleFilm.id,
                        "0bca8a77230116b8ac43cd3b8634aca9",
                        "ru-RU"
                    )
                    withContext(Dispatchers.Main) {
                        Picasso
                            .get()
                            .load("https://image.tmdb.org/t/p/w500/" + movie?.posterPath)
                            .fit()
                            .into(favoriteFilmImage)

                        favoriteFilmTitle.text = movie?.title

                        favoriteReleaseDate.text = movie?.releaseDate
                    }
                }

                root.setOnClickListener {
                    favoriteClickListener?.onFavoriteClickListener(favoriteData[adapterPosition])
                }
            }


        fun getBinding(): FavoriteCardMaketBinding {
            return binding2
        }
    }

    fun interface FavoriteClickListener {
        fun onFavoriteClickListener(favoriteFilmEntity: FavoriteFilmEntity)
    }

    fun removeListener() {
        favoriteClickListener = null
    }
}