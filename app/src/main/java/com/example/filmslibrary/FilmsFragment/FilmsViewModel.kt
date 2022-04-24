package com.example.filmslibrary.FilmsFragment

import android.util.Log
import androidx.lifecycle.*
import com.example.filmslibrary.model.AppState
import com.example.filmslibrary.model.dataSource.DataSource
import com.example.filmslibrary.model.repository.FilmObject
import com.example.filmslibrary.model.repository.FilmsRepositoryInterface
import kotlinx.coroutines.Dispatchers
import java.lang.Thread.sleep
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.lang.Thread.sleep

class FilmsViewModel(private val repositoryInterface: FilmsRepositoryInterface<DataSource<List<FilmObject>>>) :
    ViewModel(), LifecycleObserver {

    private val myLiveData: MutableLiveData<AppState> = MutableLiveData()

    fun getMyLiveData() = myLiveData

    fun getFilms() {
        myLiveData.value = AppState.Loading


        viewModelScope.async(Dispatchers.IO) {
            sleep(1000)
            myLiveData.postValue(AppState.Success(repositoryInterface.getListOfFilmsFromInternetAsync(apiKey:String, )))
        }
    }
}

