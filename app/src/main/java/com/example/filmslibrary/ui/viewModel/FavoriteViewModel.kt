package com.example.filmslibrary.ui.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.filmslibrary.BuildConfig
import com.example.filmslibrary.application.App
import com.example.filmslibrary.model.firebaseDb.FirebaseDbManager
import com.example.filmslibrary.model.data.AppState
import com.example.filmslibrary.model.repository.FilmObject
import com.example.filmslibrary.model.repository.FilmsList
import com.example.filmslibrary.model.repository.FilmsRepositoryInterface
import com.example.filmslibrary.room.entity.FavoriteFilmEntity
import com.example.filmslibrary.room.repository.FavoriteFilmDao
import com.example.filmslibrary.room.service.FavoriteService
import com.example.filmslibrary.ui.recyclerViewAdapters.FavoriteAdapter
import com.example.filmslibrary.utils.favouriteFilmFirebaseToFavouriteFilmEntity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class FavoriteViewModel(
    private val filmsRepositoryInterface: FilmsRepositoryInterface<FilmsList, FilmObject>,
    private val firebaseDbManager: FirebaseDbManager
) :
    BaseViewModel<AppState>() {

    private var favoriteFilmDao: FavoriteFilmDao = App.getFavoriteFilmDao()
    private var favoriteLiveData: MutableLiveData<AppState> = MutableLiveData()
    private var favoriteFilmService: FavoriteService = FavoriteService(favoriteFilmDao)
    private var adapter: FavoriteAdapter? = null
    private val apiKey: String = BuildConfig.API_KEY


    fun getFavoriteLiveData() = favoriteLiveData
    fun getFavoriteList() {
        favoriteLiveData.value = AppState.Loading(null)
        cancelJob()

        val auth = Firebase.auth
        if (auth.uid != null) {
            firebaseDbManager.getFromDb(FirebaseDbManager.FireBaseCallback {
                viewModelCoroutineScope.launch(Dispatchers.IO) {
                    val result = getMovieFromServer(favouriteFilmFirebaseToFavouriteFilmEntity(it))

                    favoriteLiveData.postValue(
                        AppState.FavoriteSuccess(result)
                    )
                }
            })

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


    override fun handleError(throwable: Throwable) {
        favoriteLiveData.postValue(AppState.Error(throwable))
    }

    override fun onCleared() {
//        favoriteLiveData.value = AppState.FavoriteSuccess(null)
        super.onCleared()
    }

}