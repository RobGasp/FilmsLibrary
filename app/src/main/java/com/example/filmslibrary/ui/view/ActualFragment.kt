package com.example.filmslibrary.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.filmslibrary.databinding.FragmentActualBinding
import com.example.filmslibrary.model.data.AppState
import com.example.filmslibrary.model.repository.FilmObject
import com.example.filmslibrary.ui.recyclerViewAdapters.ActualFilmsAdapter
import com.example.filmslibrary.ui.viewModel.FilmsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ActualFragment : Fragment() {

    private var _binding: FragmentActualBinding? = null
    private val binding get() = _binding!!

    private val filmsViewModel: FilmsViewModel by viewModel()
    private var recyclerView: RecyclerView? = null
    private var adapter: ActualFilmsAdapter? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentActualBinding.inflate(inflater, container, false)
        recyclerView = binding.recyclerViewActualFragment
        return binding.root
    }

//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//        if (context is FilmClickListener) {
//            filmClickListener = context
//        }
//    }

    override fun onDetach() {
        adapter?.removeListener()
        super.onDetach()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()


        filmsViewModel.getMyLiveData().observe(requireActivity()) {
            renderData(it)
        }

        filmsViewModel.getFilms("0bca8a77230116b8ac43cd3b8634aca9", "ru-RU")
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
        val lm = GridLayoutManager(context, 2)
        recyclerView?.layoutManager = lm
        adapter = ActualFilmsAdapter()
        recyclerView?.adapter = adapter
        adapter?.filmClickListener = ActualFilmsAdapter.FilmClickListener { movie ->
            filmClicked(movie)
        }
    }

    private fun renderData(appState: AppState) = with(binding) {
        when (appState) {
            is AppState.Success -> {
                loadingLayout.visibility = View.GONE
                adapter?.setFilm(appState.filmsData)
            }
            is AppState.Loading -> {
                loadingLayout.visibility = View.VISIBLE
            }
            is AppState.Error -> {
                loadingLayout.visibility = View.GONE
            }
        }
    }


    fun filmClicked(film: FilmObject) {
        val action =
            ActualFragmentDirections.actionActualFragmentToDetailsPageFragment(movie = film)
        view?.findNavController()?.navigate(action)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}