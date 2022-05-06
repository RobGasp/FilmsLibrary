package com.example.filmslibrary.di

import com.example.filmslibrary.ui.viewModel.FilmsViewModel
import com.example.filmslibrary.model.dataSource.RetrofitImpl
import com.example.filmslibrary.model.repository.FilmObject
import com.example.filmslibrary.model.repository.FilmsList
import com.example.filmslibrary.model.repository.FilmsRepository
import com.example.filmslibrary.model.repository.FilmsRepositoryInterface
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val application = module {
    single<FilmsRepositoryInterface<FilmsList>> { FilmsRepository(RetrofitImpl()) }
}


val filmsModule = module {
    viewModel { FilmsViewModel(get()) }
}