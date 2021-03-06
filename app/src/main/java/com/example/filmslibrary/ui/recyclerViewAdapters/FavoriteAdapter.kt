package com.example.filmslibrary.ui.recyclerViewAdapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.filmslibrary.BuildConfig
import com.example.filmslibrary.R
import com.example.filmslibrary.databinding.FavoriteCardMaketBinding
import com.example.filmslibrary.model.repository.FilmObject
import com.example.filmslibrary.model.repository.FilmsList
import com.example.filmslibrary.model.repository.FilmsRepositoryInterface
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope

class FavoriteAdapter : RecyclerView.Adapter<FavoriteAdapter.FavoriteHolder>(),
    CoroutineScope by MainScope() {

    var favoriteClickListener: FavoriteClickListener? = null
    private var favoriteData: MutableList<FilmObject> = mutableListOf()

    private var imageSourcePath = BuildConfig.IMAGE_SOURCE_PATH

    @SuppressLint("NotifyDataSetChanged")
    fun setFavorite(favoriteFilmEntity: List<FilmObject>) {
        favoriteData.clear()
        favoriteData = favoriteFilmEntity.toMutableList()
        notifyDataSetChanged()
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

        fun bind(favoriteSingleFilm: FilmObject, field: FavoriteCardMaketBinding) =
            with(field) {
                Picasso
                    .get()
                    .load(imageSourcePath + favoriteSingleFilm.posterPath)
                    .resizeDimen(R.dimen.film_cover_width, R.dimen.film_cover_height)
                    .centerInside()
                    .into(favoriteFilmImage)

                favoriteFilmTitle.text = favoriteSingleFilm.title

                favoriteReleaseDate.text = favoriteSingleFilm.releaseDate

                root.setOnClickListener {
                    favoriteClickListener?.onFavoriteClickListener(favoriteData[adapterPosition])
                }
            }


        fun getBinding(): FavoriteCardMaketBinding {
            return binding2
        }
    }

    fun interface FavoriteClickListener {
        fun onFavoriteClickListener(favoriteFilmEntity: FilmObject)
    }

    fun removeListener() {
        favoriteClickListener = null
    }
}