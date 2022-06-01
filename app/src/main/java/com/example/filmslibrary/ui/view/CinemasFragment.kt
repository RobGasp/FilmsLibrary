package com.example.filmslibrary.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.filmslibrary.R
import com.example.filmslibrary.databinding.FragmentCinemasBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class CinemasFragment : Fragment() {

    private var _binding: FragmentCinemasBinding? = null
    private val binding get() = _binding!!
    private val callback = OnMapReadyCallback { googleMap ->
        val moscow = LatLng(55.45, 37.37)
        googleMap.addMarker(MarkerOptions().position(moscow).title("Moscow"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(moscow))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentCinemasBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.cinemas_map_fragment) as
                SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}