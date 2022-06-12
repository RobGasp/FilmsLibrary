package com.example.filmslibrary.ui.recyclerViewAdapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.filmslibrary.R
import com.example.filmslibrary.databinding.HistoryCardMaketBinding
import com.example.filmslibrary.model.mapper.FilmDtoMapper
import com.example.filmslibrary.room.entity.HistoryEntity
import com.squareup.picasso.Picasso

class HistoryAdapter : RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {

    var historyClickListener: HistoryClickListener? = null
    private var historyData: List<HistoryEntity> = listOf()


    fun setHistory(historyList: List<HistoryEntity>) {
        historyData = historyList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val binding = HistoryCardMaketBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return HistoryViewHolder(binding.root, binding)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.bind(historyData[position], holder.getBinding())
    }

    override fun getItemCount(): Int {
        return historyData.size
    }

    inner class HistoryViewHolder(itemView: View, binding: HistoryCardMaketBinding) :
        RecyclerView.ViewHolder(itemView) {
        private var binding2 = binding

        fun bind(singleHistoryFilm: HistoryEntity, field: HistoryCardMaketBinding) = with(field) {

            Picasso
                .get()
                .load(
                    "https://image.tmdb.org/t/p/w500/" + FilmDtoMapper.historyEntityToFilmObject(
                        singleHistoryFilm
                    ).posterPath
                )
                .resizeDimen(R.dimen.film_cover_width,R.dimen.film_cover_height)
                .centerInside()
                .into(historyFilmImage)

            historyFilmTitle.text = FilmDtoMapper.historyEntityToFilmObject(singleHistoryFilm).title

            historyDateRequest.text = singleHistoryFilm.dateRequest

            root.setOnClickListener {
                historyClickListener?.onHistoryClickListener(historyData[adapterPosition])
            }
        }

        fun getBinding(): HistoryCardMaketBinding {
            return binding2
        }
    }

    fun interface HistoryClickListener {
        fun onHistoryClickListener(historyEntity: HistoryEntity)
    }

    fun removeListener() {
        historyClickListener = null
    }


}