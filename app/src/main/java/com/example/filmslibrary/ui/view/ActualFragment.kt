package com.example.filmslibrary.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.filmslibrary.databinding.FragmentActualBinding
import com.example.filmslibrary.model.repository.FilmObject

class ActualFragment : Fragment() {

    private var _binding: FragmentActualBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentActualBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.textView.setOnClickListener {

            //болванка фильма
            val movie = FilmObject(title = "Movie from actual fragment")


            //Эти две строки для открытия нового фргамента с описанием фильма, поместить потом в адаптер
            // в клик листенер для элемента списка
            //не забудьте передать в него обьект фильма выбранного, пока я сделал на существующий DTO класс,
            //как помеянете класс, я изменю его.
            val action = ActualFragmentDirections.actionActualFragmentToDetailsPageFragment(movie = movie)
            view.findNavController().navigate(action)

        }

//        binding.topAppBar.setOnMenuItemClickListener { menuItem ->
//            when (menuItem.itemId) {
//                R.id.search -> {
//                    //TODO Поиск
//                    true
//                }
//                else -> false
//            }
//        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}