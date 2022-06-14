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
import kotlinx.coroutines.launch

class FavoriteViewModel(private val filmsRepositoryInterface: FilmsRepositoryInterface<FilmsList, FilmObject>, private val firebaseDbManager: FirebaseDbManager) :
    BaseViewModel<AppState>() {

    private var favoriteFilmDao: FavoriteFilmDao = App.getFavoriteFilmDao()
    private var favoriteLiveData: MutableLiveData<AppState> = MutableLiveData()
    private var favoriteFilmService: FavoriteService = FavoriteService(favoriteFilmDao)
    private var adapter: FavoriteAdapter? = null
    private val apiKey:String = BuildConfig.API_KEY
    private var films: List<FavoriteFilmEntity>? = listOf()

    fun getFavoriteLiveData() = favoriteLiveData
    fun getFavoriteList() {
        firebaseDbManager.fireBaseCallback = FirebaseDbManager.FireBaseCallback {
            films = favouriteFilmFirebaseToFavouriteFilmEntity(it)
        }
        favoriteLiveData.value = AppState.Loading(null)
        cancelJob()
//        adapter?.setFilmsRepositoryInterface(filmsRepositoryInterface)
        viewModelCoroutineScope.launch(Dispatchers.IO) {
            Log.d("TAGG", "before")
            val auth = Firebase.auth
            if(auth.uid != null){

                firebaseDbManager.getFromDb()
                films?.let { favoriteFilmService.addAllFavoriteFilms(it) }
                val updatedFilmsFromDb = favoriteFilmService.getAllFavoriteFilms()
                showFilms(updatedFilmsFromDb)
                firebaseDbManager.postToDb(updatedFilmsFromDb)

            } else{
                Log.d("TAGG", "falsecycle")
                val updatedFilmsFromDb = favoriteFilmService.getAllFavoriteFilms()
                showFilms(updatedFilmsFromDb)
            }
            Log.d("TAGG", "end")

        }
    }

    private suspend fun showFilms(list: List<FavoriteFilmEntity>){
        favoriteLiveData.postValue(
            AppState.FavoriteSuccess(getMovieFromServer(list)))
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