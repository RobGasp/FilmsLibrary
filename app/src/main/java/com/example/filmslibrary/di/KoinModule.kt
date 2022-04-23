package com.example.filmslibrary.di

import com.example.filmslibrary.FilmsFragment.FilmsViewModel
import com.example.filmslibrary.repository.FilmsRepository
import com.example.filmslibrary.repository.FilmsRepositoryInterface
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single <FilmsRepositoryInterface> { FilmsRepository() }

    viewModel { FilmsViewModel(get()) }
}