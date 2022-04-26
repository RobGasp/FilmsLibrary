package com.example.filmslibrary

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.filmslibrary.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.bottom_view_actual -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, ActualFragment())
                        .commitAllowingStateLoss()
                    true
                }
                R.id.bottom_view_favourite -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, FavouriteFragment())
                        .commitAllowingStateLoss()
                    true
                }
                R.id.bottom_view_cinemas -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, CinemasFragment())
                        .commitAllowingStateLoss()
                    true
                }
                else -> false
            }
        }
        binding.bottomNavigationView.selectedItemId = R.id.bottom_view_actual
    }
}