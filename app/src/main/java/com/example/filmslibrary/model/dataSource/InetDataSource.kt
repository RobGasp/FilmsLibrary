package com.example.filmslibrary.model.dataSource

import kotlinx.coroutines.Deferred

interface InetDataSource<T> {

    suspend fun getDataAsync(apiKey: String, language: String): Deferred<T>
}