package com.example.filmslibrary

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation_view)
        bottomNavigationView.setOnItemSelectedListener { item ->
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
        bottomNavigationView.selectedItemId = R.id.bottom_view_actual
    }
}