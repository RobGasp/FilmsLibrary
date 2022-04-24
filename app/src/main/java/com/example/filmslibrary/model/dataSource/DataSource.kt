package com.example.filmslibrary.model.dataSource

import kotlinx.coroutines.Deferred

interface DataSource<T> {

    suspend fun getDataAsync(apiKey: String, language: String): Deferred<T>
}