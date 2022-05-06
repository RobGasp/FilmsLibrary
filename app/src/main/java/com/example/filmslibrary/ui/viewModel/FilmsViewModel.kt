package com.example.filmslibrary.ui.viewModel

import androidx.lifecycle.*
import com.example.filmslibrary.model.data.AppState
import com.example.filmslibrary.model.dataSource.InetDataSource
import com.example.filmslibrary.model.repository.FilmObject
import com.example.filmslibrary.model.repository.FilmsList
import com.example.filmslibrary.model.repository.FilmsRepositoryInterface
import kotlinx.coroutines.*
import java.lang.Thread.sleep

class FilmsViewModel(private val repositoryInterface: FilmsRepositoryInterface<InetDataSource<List<FilmObject>>>) :
    ViewModel(), LifecycleObserver {

    private val myLiveData: MutableLiveData<AppState> = MutableLiveData()

    fun getMyLiveData() = myLiveData

    fun getFilms(apiKey: String, language: String) {
        myLiveData.value = AppState.Loading(null)
        cancelJob()

        viewModelScope.launch(Dispatchers.IO) {
            myLiveData.postValue(
                AppState.Success(
                    repositoryInterface.getListOfFilmsFromInternetAsync(
                        apiKey,
                        language
                    ).getFilmsList()
                )
            )
        }
    }

    override fun onCleared() {
        myLiveData.value = AppState.Success(listOf())
        super.onCleared()
    }

    private fun cancelJob() {
        viewModelScope.coroutineContext.cancelChildren()
    }
}

