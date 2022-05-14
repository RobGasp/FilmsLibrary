package com.example.filmslibrary.ui.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.filmslibrary.databinding.FragmentDetailsPageBinding
import com.example.filmslibrary.model.repository.FilmObject
import com.squareup.picasso.Picasso


class DetailsPageFragment : Fragment() {

    companion object {
        val MOVIE = "movie"
    }


    private var _binding: FragmentDetailsPageBinding? = null
    private val binding get() = _binding!!

    private lateinit var movie: FilmObject

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            movie = it.getParcelable(MOVIE)!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        super.onViewCreated(view, savedInstanceState)

        Picasso
            .get()
            .load("https://image.tmdb.org/t/p/w500/" + movie.posterPath)
            .fit()
            .into(cover)

        title.text = movie.title
        year.text = movie.releaseDate
        mediaType.text = movie.mediaType
        voteAverage.text = "Рейтинг фильма: ${movie.voteAverage}"
        description.text = movie.overview
    }
}