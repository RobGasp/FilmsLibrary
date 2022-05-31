package com.example.filmslibrary.application

import android.app.Application
import androidx.room.Room
import com.example.filmslibrary.di.application
import com.example.filmslibrary.di.filmsModule
import com.example.filmslibrary.room.FilmotekaDataBase
import com.example.filmslibrary.room.repository.CacheFilmDao
import com.example.filmslibrary.room.repository.FavoriteFilmDao
import com.example.filmslibrary.room.repository.HistoryDao
import org.koin.core.context.startKoin
import java.lang.IllegalStateException

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(application, filmsModule)
        }
        appInstance = this
    }

    companion object {
        private var appInstance: App? = null
        private var db: FilmotekaDataBase? = null
        private const val DB_NAME = "History1.db"

        fun getHistoryDao(): HistoryDao {
            if (db == null) {
                synchronized(FilmotekaDataBase::class.java) {
                    if (db == null) {
                        if (appInstance == null) {
                            throw IllegalStateException("Application ids null meanwhile creating database")
                        }
                        db = Room.databaseBuilder(
                            appInstance!!.applicationContext,
                            FilmotekaDataBase::class.java,
                            DB_NAME
                        )
                            .allowMainThreadQueries()
                            .build()
                    }
                }
            }
            return db!!.historyDao()
        }

        fun getCacheDao(): CacheFilmDao {
            if (db == null) {
                synchronized(FilmotekaDataBase::class.java) {
                    if (db == null) {
                        if (appInstance == null) {
                            throw IllegalStateException("Application ids null meanwhile creating database")
                        }
                        db = Room.databaseBuilder(
                            appInstance!!.applicationContext,
                            FilmotekaDataBase::class.java,
                            DB_NAME
                        )
                            .allowMainThreadQueries()
                            .build()
                    }
                }
            }
            return db!!.cacheDao()
        }

        fun getFavoriteFilmDao(): FavoriteFilmDao {
            if (db == null) {
                synchronized(FilmotekaDataBase::class.java) {
                    if (db == null) {
                        if (appInstance == null) {
                            throw IllegalStateException("Application ids null meanwhile creating database")
                        }
                        db = Room.databaseBuilder(
                            appInstance!!.applicationContext,
                            FilmotekaDataBase::class.java,
                            DB_NAME
                        )
                            .allowMainThreadQueries()
                            .build()
                    }
                }
            }
            return db!!.favoriteDao()
        }
    }
}