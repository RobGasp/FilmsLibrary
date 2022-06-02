package com.example.filmslibrary.ui.recyclerViewAdapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.filmslibrary.databinding.FavoriteCardMaketBinding
import com.example.filmslibrary.room.entity.FavoriteFilmEntity
import com.squareup.picasso.Picasso

class FavoriteAdapter:RecyclerView.Adapter<FavoriteAdapter.FavoriteHolder>() {

    private var favoriteClickListener:FavoriteClickListener?=null
    private var favoriteData:List<FavoriteFilmEntity> = listOf()

    @SuppressLint("NotifyDataSetChanged")
    fun setFavorite(favoriteFilmEntity: List<FavoriteFilmEntity>){
        favoriteData = favoriteFilmEntity
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteHolder{
       val binding = FavoriteCardMaketBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return  FavoriteHolder(binding.root, binding)
    }

    override fun onBindViewHolder(holder: FavoriteHolder, position: Int) {
        holder.bind(favoriteData[position],holder.getBinding())
    }

    override fun getItemCount(): Int {
        return favoriteData.size
    }
    inner class FavoriteHolder(itemView: View, binding: FavoriteCardMaketBinding):RecyclerView.ViewHolder(itemView){

        private var binding2 = binding

        fun bind(favoriteSingleFilm:FavoriteFilmEntity, field:FavoriteCardMaketBinding) = with(field){

//            Picasso
//                .get()
//                .load("https://image.tmdb.org/t/p/w500/" + favoriteSingleFilm.)
//                .fit()
//                .into(favoriteFilmImage)
//            favoriteFilmTitle.text = favoriteSingleFilm
//
//            favoriteReleaseDate.text = favoriteSingleFilm

            root.setOnClickListener {
                favoriteClickListener?.onFavoriteClickListener(favoriteData[adapterPosition] )
            }
        }



        fun getBinding():FavoriteCardMaketBinding{
            return binding2
        }
    }

    interface FavoriteClickListener{
        fun onFavoriteClickListener (favoriteFilmEntity: FavoriteFilmEntity)
    }

    fun removeListener(){
        favoriteClickListener = null
    }
}