package com.example.filmslibrary.ui.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.viewModelScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.filmslibrary.databinding.FragmentActualBinding
import com.example.filmslibrary.model.data.AppState
import com.example.filmslibrary.model.repository.FilmObject
import com.example.filmslibrary.ui.FilmClickListener
import com.example.filmslibrary.ui.recyclerViewAdapters.ActualFilmsAdapter
import com.example.filmslibrary.ui.viewModel.FilmsViewModel
import kotlinx.coroutines.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.net.URL

class ActualFragment : Fragment(), FilmClickListener {

    private var _binding: FragmentActualBinding? = null
    private val binding get() = _binding!!

    private val filmsViewModel: FilmsViewModel by viewModel()
    private var filmClickListener: FilmClickListener? = null
    private var recyclerView: RecyclerView? = null
    private var adapter: ActualFilmsAdapter? = null
    private var filmsList: List<FilmObject>? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentActualBinding.inflate(inflater, container, false)
        recyclerView = binding.recyclerViewActualFragment
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FilmClickListener) {
            filmClickListener = context
        }
    }

    override fun onDetach() {
        filmClickListener = null
        super.onDetach()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        filmsViewModel.getMyLiveData().observe(requireActivity()) {
            renderData(it)
        }

        filmsViewModel.getFilms("0bca8a77230116b8ac43cd3b8634aca9", "ru-RU")

        filmsList?.let { adapter?.setFilm(it) }

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

    private fun initRecyclerView() {
        val lm = GridLayoutManager(context, 3)
        recyclerView?.layoutManager = lm
        adapter = ActualFilmsAdapter()
        recyclerView?.adapter = adapter
        filmClickListener?.let { adapter?.setOnFilmClickListener(it) }
    }

    private fun renderData(appState: AppState) = with(binding) {
        when (appState) {
            is AppState.Success -> {
                loadingLayout.visibility = View.GONE
                filmsList = appState.filmsData
            }
            is AppState.Loading -> {
                loadingLayout.visibility = View.VISIBLE
            }
            is AppState.Error -> {
                loadingLayout.visibility = View.GONE
            }
        }
    }


    override fun filmClicked(film: FilmObject) {

        //Эти две строки для открытия нового фргамента с описанием фильма, поместить потом в адаптер
        // в клик листенер для элемента списка
        //не забудьте передать в него обьект фильма выбранного, пока я сделал на существующий DTO класс,
        //как помеянете класс, я изменю его.
        //я сделал все, как надо, комментарии оставил для справки
        val action =
            ActualFragmentDirections.actionActualFragmentToDetailsPageFragment(movie = film)
        view?.findNavController()?.navigate(action)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}