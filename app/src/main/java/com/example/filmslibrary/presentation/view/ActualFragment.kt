package com.example.filmslibrary.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.filmslibrary.R
import com.example.filmslibrary.databinding.FragmentActualBinding
import com.example.filmslibrary.presentation.view.adapter.ActualFragmentAdapter

class ActualFragment : Fragment() {

    private var _binding: FragmentActualBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentActualBinding.inflate(inflater, container, false)
        val data: Array<String> = resources.getStringArray(R.array.films)
        initRecyclerView(binding.recyclerViewActual, data)
        return binding.root
    }

    private fun initRecyclerView(recyclerView: RecyclerView, data: Array<String>) {
        recyclerView.setHasFixedSize(true)
        val layoutManager = GridLayoutManager(context, 3, RecyclerView.VERTICAL, false)
        recyclerView.layoutManager = layoutManager
        val adapter = ActualFragmentAdapter(data)
        recyclerView.adapter = adapter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.topAppBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.search -> {
                    //TODO Поиск
                    true
                }
                else -> false
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}