package com.example.filmslibrary.FilmsFragment

import androidx.lifecycle.*
import com.example.filmslibrary.model.AppState
import com.example.filmslibrary.model.dataSource.DataSource
import com.example.filmslibrary.model.repository.FilmObject
import com.example.filmslibrary.model.repository.FilmsRepositoryInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

class FilmsViewModel(private val repositoryInterface: FilmsRepositoryInterface<DataSource<List<FilmObject>>>) :
    ViewModel(), LifecycleObserver {

    private val myLiveData: MutableLiveData<AppState> = MutableLiveData()

    fun getMyLiveData() = myLiveData

    suspend fun getFilms(apiKey:String, language:String) {
        myLiveData.value = AppState.Loading(null)
  
        viewModelScope.async(Dispatchers.IO) {
            myLiveData.postValue(AppState.Success(repositoryInterface.getListOfFilmsFromInternetAsync(apiKey,language)))
        }.await()
    }

    override fun onCleared() {
        myLiveData.value = AppState.Success(null)
        super.onCleared()
    }
}

