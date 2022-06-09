package com.example.filmslibrary.ui.viewModel

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import com.example.filmslibrary.model.data.AppState
import com.example.filmslibrary.model.dataSource.InetDataSource
import com.example.filmslibrary.model.repository.FilmObject
import com.example.filmslibrary.model.repository.FilmsList
import com.example.filmslibrary.model.repository.FilmsRepositoryInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FilmsViewModel(private val repositoryInterface: FilmsRepositoryInterface<FilmsList,FilmObject>) :
    BaseViewModel<AppState>(), LifecycleObserver {

    private val myLiveData: MutableLiveData<AppState> = MutableLiveData()

    fun getMyLiveData() = myLiveData

    fun getFilms(apiKey: String, language: String) {
        myLiveData.value = AppState.Loading(null)
        cancelJob()

        viewModelCoroutineScope.launch(Dispatchers.IO) {
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

    override fun handleError(throwable: Throwable) {
        myLiveData.postValue(AppState.Error(throwable))
    }

    override fun onCleared() {
        myLiveData.value = AppState.Success(listOf())
        super.onCleared()
    }

}

