package com.example.filmslibrary.ui.view

import android.app.ActionBar
import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.Toast
import android.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.filmslibrary.R
import com.example.filmslibrary.databinding.FragmentActualBinding
import com.example.filmslibrary.model.data.AppState
import com.example.filmslibrary.model.repository.FilmObject
import com.example.filmslibrary.ui.recyclerViewAdapters.ActualFilmsAdapter
import com.example.filmslibrary.ui.viewModel.FilmsViewModel
import kotlinx.coroutines.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.component.getScopeId
import java.net.URL

class ActualFragment : Fragment() {

    private var _binding: FragmentActualBinding? = null
    private val binding get() = _binding!!

    private lateinit var navController:NavController
    private lateinit var navHostFragment: NavHostFragment

    private val filmsViewModel: FilmsViewModel by viewModel()
    private var recyclerView: RecyclerView? = null
    private var adapter: ActualFilmsAdapter? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)

        super.onCreate(savedInstanceState)
    }

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

        navController = Navigation.findNavController(requireActivity(), R.id.fragment_nav_host);

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
        val lm = GridLayoutManager(context, 3)
        recyclerView?.layoutManager = lm
        adapter = ActualFilmsAdapter()
        recyclerView?.adapter = adapter
        adapter?.filmClickListener = ActualFilmsAdapter.FilmClickListener {
            movie -> filmClicked(movie)
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
            else -> {}
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.actual_fragment_options_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val gotoHistoryFragment = ActualFragmentDirections.actionActualFragmentToHistoryPageFragment()
        when(item.itemId){
            R.id.history -> view?.findNavController()?.navigate(gotoHistoryFragment)


        //Navigation.createNavigateOnClickListener(R.id.history_page_fragment)
                //NavigationUI.onNavDestinationSelected(R.id.history_page_fragment,navController)
        }
        return super.onOptionsItemSelected(item)
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