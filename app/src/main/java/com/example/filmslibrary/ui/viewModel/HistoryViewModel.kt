package com.example.filmslibrary.ui.viewModel

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import com.example.filmslibrary.application.App
import com.example.filmslibrary.model.data.AppState
import com.example.filmslibrary.room.repository.HistoryDao
import com.example.filmslibrary.room.service.HistoryService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


    class HistoryViewModel() : BaseViewModel<AppState>(), LifecycleObserver {

        private var historyDao: HistoryDao= App.getHistoryDao()
        private val historyLiveData: MutableLiveData<AppState> = MutableLiveData()
        private val historyService: HistoryService = HistoryService(historyDao)

    fun getHistoryLiveData() = historyLiveData

    fun getHistoryList() {
        historyLiveData.postValue(AppState.Loading(null))
        cancelJob()
        viewModelCoroutineScope.launch(Dispatchers.IO) {
            historyLiveData.postValue(
                AppState.HistorySuccess(
                    historyService.getAllCache()
                )
            )
        }
    }

    override fun handleError(throwable: Throwable) {
        historyLiveData.postValue(AppState.Error(throwable))
    }

    override fun onCleared() {
        historyLiveData.value = AppState.HistorySuccess(listOf())
        super.onCleared()
    }
}