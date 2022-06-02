package com.example.filmslibrary.ui.viewModel

import androidx.lifecycle.MutableLiveData
import com.example.filmslibrary.application.App
import com.example.filmslibrary.model.data.AppState
import com.example.filmslibrary.room.repository.FavoriteFilmDao
import com.example.filmslibrary.room.service.FavoriteService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoriteViewModel: BaseViewModel<AppState>() {

    private var favoriteFilmDao: FavoriteFilmDao = App.getFavoriteFilmDao()
    private var favoriteLiveData:MutableLiveData<AppState> = MutableLiveData()
    private var favoriteFilmService: FavoriteService = FavoriteService(favoriteFilmDao)

    fun  getFavoriteLiveData() = favoriteLiveData

    fun getFavoriteList(){
        cancelJob()
        viewModelCoroutineScope.launch (Dispatchers.IO){
            favoriteLiveData.postValue(
                AppState.FavoriteSucess(favoriteFilmService.getAllFavoriteFilms())
            )
        }
    }

    override fun handleError(throwable: Throwable) {
        favoriteLiveData.postValue(AppState.Error(throwable))
    }

    override fun onCleared() {
        favoriteLiveData.value = AppState.FavoriteSucess(listOf())
        super.onCleared()
    }
}