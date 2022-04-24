package com.example.filmslibrary.di

import com.example.filmslibrary.FilmsFragment.FilmsViewModel
import com.example.filmslibrary.model.dataSource.DataSource
import com.example.filmslibrary.model.repository.FilmObject
import com.example.filmslibrary.model.repository.FilmsRepository
import com.example.filmslibrary.model.repository.FilmsRepositoryInterface
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val filmsModule = module {


    single <FilmsRepositoryInterface<List<FilmObject>>> { FilmsRepository(get()) }

    viewModel { FilmsViewModel(get()) }
}