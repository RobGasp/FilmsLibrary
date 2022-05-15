package com.example.filmslibrary.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.filmslibrary.R
import com.example.filmslibrary.databinding.FragmentHistoryBinding

class HistoryFragment : Fragment() {

    private var _binding:FragmentHistoryBinding?=null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHistoryBinding.inflate(inflater,container,false)

        return inflater.inflate(R.layout.fragment_history, container, false)
    }

}