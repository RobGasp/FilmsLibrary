package com.example.filmslibrary.model.dataSource


import com.example.filmslibrary.model.repository.FilmObject
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.InetSocketAddress
import java.net.Proxy

class RetrofitImpl : InetDataSource<List<FilmObject>> {

    private val proxyHost = "208.102.51.6"
    private val proxyPort = 58208


    private val proxy: Proxy = Proxy(Proxy.Type.HTTP, InetSocketAddress(proxyHost, proxyPort))

    override suspend fun getDataAsync(
        apiKey: String,
        language: String
    ): List<FilmObject> {
        return getService(BaseInterceptor.interceptor).getListOfFilmsAsync(apiKey, language).await()
    }

    private fun getService(interceptor: Interceptor): ApiService {
        return createRetrofit(interceptor).create(ApiService::class.java)
    }

    private fun createRetrofit(interceptor: Interceptor): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_LIST_OF_FILMS_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(createHTPPClient(interceptor))
            .build()
    }

    private fun createHTPPClient(interceptor: Interceptor): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        httpClient.proxy(proxy)
        httpClient.addInterceptor(interceptor)
        httpClient.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        return httpClient.build()
    }

    companion object {
        private const val BASE_LIST_OF_FILMS_URL = "https://api.themoviedb.org/3/"
    }
}