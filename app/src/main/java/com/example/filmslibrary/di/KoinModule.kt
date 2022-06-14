package com.example.filmslibrary.di

import com.example.filmslibrary.model.firebaseDb.FirebaseDbManager
import com.example.filmslibrary.model.dataSource.RetrofitImpl
import com.example.filmslibrary.model.repository.*
import com.example.filmslibrary.ui.viewModel.FavoriteViewModel
import com.example.filmslibrary.ui.viewModel.FilmsViewModel
import com.example.filmslibrary.ui.viewModel.HistoryViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val application = module {
    single<FilmsRepositoryInterface<FilmsList, FilmObject>> { FilmsRepository(RetrofitImpl()) }
    single<FirebaseDbManager> { FirebaseDbManager() }

}


val filmsModule = module {
    viewModel { FilmsViewModel(get()) }
    single { HistoryViewModel() }
    viewModel { FavoriteViewModel(get(),get()) }
}