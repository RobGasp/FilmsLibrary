package com.example.filmslibrary.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.filmslibrary.databinding.FragmentFavouriteBinding
import com.example.filmslibrary.model.repository.FilmObject

class FavouriteFragment : Fragment() {

    private var _binding: FragmentFavouriteBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentFavouriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.textToDelete.setOnClickListener {

            //болванка фильма
            val movie = FilmObject(title = "Movie from Favourite Fragment")


            //Эти две строки для открытия нового фргамента с описанием фильма, поместить потом в адаптер
            // в клик листенер для элемента списка
            //не забудьте передать в него обьект фильма выбранного, пока я сделал на существующий DTO класс,
            //как помеянете класс, я изменю его.
            val action = FavouriteFragmentDirections.actionFavouriteFragmentToDetailsPageFragment(movie = movie)
            view.findNavController().navigate(action)

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}