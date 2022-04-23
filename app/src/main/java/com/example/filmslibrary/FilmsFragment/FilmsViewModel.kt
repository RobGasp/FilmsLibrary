package com.example.filmslibrary.FilmsFragment

import androidx.lifecycle.*
import com.example.filmslibrary.AppState
import com.example.filmslibrary.repository.FilmsRepositoryInterface
import kotlinx.coroutines.Dispatchers
import java.lang.Thread.sleep

class FilmsViewModel(private val repositoryInterface: FilmsRepositoryInterface) :
    ViewModel(), LifecycleObserver {

    private val myLiveData: MutableLiveData<AppState> = MutableLiveData()

    fun getMyLiveData() = myLiveData

    fun getFilms() {
        myLiveData.value = AppState.Loading

        viewModelScope.launch(Dispatchers.IO) {
            sleep(1000)
            myLiveData.postValue(AppState.Success(repositoryInterface.getListOfFilmsFromInternet()))
        }
    }

}