package com.example.filmslibrary.application

import android.app.Application
import com.example.filmslibrary.di.application
import com.example.filmslibrary.di.filmsModule
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(application, filmsModule)
        }
    }
}