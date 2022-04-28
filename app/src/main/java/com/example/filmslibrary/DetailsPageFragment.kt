package com.example.filmslibrary

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.filmslibrary.databinding.FragmentActualBinding
import com.example.filmslibrary.databinding.FragmentDetailsPageBinding
import com.example.filmslibrary.model.repository.FilmObject


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
    ): View? {
        _binding = FragmentDetailsPageBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.textShowDelete.text = movie.title

    }


}