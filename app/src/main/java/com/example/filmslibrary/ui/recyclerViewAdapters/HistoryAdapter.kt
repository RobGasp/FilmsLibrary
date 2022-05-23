package com.example.filmslibrary.ui.recyclerViewAdapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.filmslibrary.databinding.FragmentHistoryBinding
import com.example.filmslibrary.databinding.HistoryCardMaketBinding
import com.example.filmslibrary.model.dto.FilmDto
import com.example.filmslibrary.model.repository.FilmObject
import com.example.filmslibrary.room.entity.HistoryEntity
import com.example.filmslibrary.room.service.CacheFilmService
import com.squareup.picasso.Picasso

class HistoryAdapter: RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {

    var historyClickListener: HistoryClickListener?=null
    private var historyData:List<HistoryEntity> = listOf()
    private var cacheFilmService:CacheFilmService?=null


    fun setHistory(historyList: List<HistoryEntity>){
        historyData = historyList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val binding = HistoryCardMaketBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return HistoryViewHolder(binding.root,binding)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.bind(historyData[position], holder.getBinding())
    }

    override fun getItemCount(): Int {
       return historyData.size
    }

    inner class HistoryViewHolder(itemView: View, binding:HistoryCardMaketBinding):RecyclerView.ViewHolder(itemView){
        private var binding2 = binding

        fun bind(singleHistoryFilm:HistoryEntity, field: HistoryCardMaketBinding) = with(field){
            Picasso
                .get()
                .load("https://image.tmdb.org/t/p/w500/" + cacheFilmService?.findFilmById(singleHistoryFilm.cacheFilmId)?.posterPath)
                .fit()
                .into(historyFilmImage)

                historyFilmTitle.text = cacheFilmService?.findFilmById(singleHistoryFilm.cacheFilmId)?.title
                historyDateRequest.text = singleHistoryFilm.dateRequest.toString()
        }
        fun getBinding():HistoryCardMaketBinding{
            return binding2
        }
    }

    fun interface HistoryClickListener{
        fun onHistoryClickListener(film:FilmDto)
    }

    fun removeListener(){
        historyClickListener = null
    }


}