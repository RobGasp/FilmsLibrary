package com.example.filmslibrary.ui.viewModel

import androidx.lifecycle.MutableLiveData
import com.example.filmslibrary.BuildConfig
import com.example.filmslibrary.application.App
import com.example.filmslibrary.model.FirebaseDb.FirebaseDbManager
import com.example.filmslibrary.model.data.AppState
import com.example.filmslibrary.model.repository.FilmObject
import com.example.filmslibrary.model.repository.FilmsList
import com.example.filmslibrary.model.repository.FilmsRepositoryInterface
import com.example.filmslibrary.room.entity.FavoriteFilmEntity
import com.example.filmslibrary.room.repository.FavoriteFilmDao
import com.example.filmslibrary.room.service.FavoriteService
import com.example.filmslibrary.ui.recyclerViewAdapters.FavoriteAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoriteViewModel(private val filmsRepositoryInterface: FilmsRepositoryInterface<FilmsList, FilmObject>, private val firebaseDbManager: FirebaseDbManager) :
    BaseViewModel<AppState>() {

    private var favoriteFilmDao: FavoriteFilmDao = App.getFavoriteFilmDao()
    private var favoriteLiveData: MutableLiveData<AppState> = MutableLiveData()
    private var favoriteFilmService: FavoriteService = FavoriteService(favoriteFilmDao)
    private var adapter: FavoriteAdapter? = null
    private val apiKey:String = BuildConfig.API_KEY

    fun getFavoriteLiveData() = favoriteLiveData

    fun getFavoriteList() {
        adapter?.setFilmsRepositoryInterface(filmsRepositoryInterface)
        favoriteLiveData.value = AppState.Loading(null)
        cancelJob()
        viewModelCoroutineScope.launch(Dispatchers.IO) {

            val moviesIdList: List<FavoriteFilmEntity> = favoriteFilmService.getAllFavoriteFilms()
            favoriteLiveData.postValue(
                AppState.FavoriteSuccess(getMovieFromServer(moviesIdList))
            )
            saveToFirestoreDB(moviesIdList)
        }
    }

    private suspend fun getMovieFromServer(moviesIdList: List<FavoriteFilmEntity>): List<FilmObject> {
        val list = mutableListOf<FilmObject>()
        for (id in moviesIdList) {
            val movie: FilmObject = filmsRepositoryInterface.getSingleFilmFromInternetAsync(
                id.id,
                apiKey,
                "ru-RU"
            )
            list.add(movie)
        }
        return list
    }

    private fun saveToFirestoreDB(moviesIdList: List<FavoriteFilmEntity>){
        firebaseDbManager.postToDb(moviesIdList)
    }

    override fun handleError(throwable: Throwable) {
        favoriteLiveData.postValue(AppState.Error(throwable))
    }

    override fun onCleared() {
        favoriteLiveData.value = AppState.FavoriteSuccess(listOf())
        super.onCleared()
    }
}