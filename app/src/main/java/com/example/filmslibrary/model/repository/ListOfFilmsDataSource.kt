package com.example.filmslibrary.model.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.filmslibrary.MainActivity
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Exception
import java.lang.StringBuilder
import java.net.HttpURLConnection
import java.net.URL
import java.util.stream.Collectors
import javax.net.ssl.HttpsURLConnection

class ListOfFilmsDataSource {

    var filmsArray18free = ArrayList<FilmObject>()
    var filmsArray = ArrayList<FilmObject>()
    var mainActivity = MainActivity()
    var isAdult: Boolean? = null

    fun getFilmsListFromInternet(): List<FilmObject> {

        val uri =
            URL("https://api.themoviedb.org/3/trending/movie/day?api_key=0bca8a77230116b8ac43cd3b8634aca9&language=ru-RU")

        lateinit var urlConnection: HttpURLConnection

        try {
            isAdult = mainActivity.getAdultMode()

            urlConnection = uri.openConnection() as HttpsURLConnection
            urlConnection.requestMethod = "GET"
            urlConnection.readTimeout = 10000
            val bufferedReader = BufferedReader(InputStreamReader(urlConnection.inputStream))

            val lines = if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
                getLinesForOld(bufferedReader)
            } else {
                getLines(bufferedReader)
            }

            val jsonObject = JSONObject(lines)
            val jsonArray = jsonObject.getJSONArray("results")
            val tempFilmsArray18free = ArrayList<FilmObject>()
            val tempFilmsArray = ArrayList<FilmObject>()

            for (i in 0..jsonArray.length() - 1) {
                val oneFilm = FilmObject(0, "0", "", "0", "0", 0.0, "0.0", false)
                oneFilm.id = jsonArray.getJSONObject(i).getInt("id")
                oneFilm.posterPath =
                    "https://image.tmdb.org/t/p/original" + jsonArray.getJSONObject(i)
                        .getString("poster_path")
                oneFilm.title = jsonArray.getJSONObject(i).getString("title")
                oneFilm.releaseDate = jsonArray.getJSONObject(i).getString("release_date")
                //oneFilm.mediaType = jsonArray.getJSONObject(i).getString("media_type")
                oneFilm.voteAverage = jsonArray.getJSONObject(i).getDouble("vote_average")
                oneFilm.overview = jsonArray.getJSONObject(i).getString("overview")
                oneFilm.adult =
                    jsonArray.getJSONObject(i).getBoolean("adult")

                tempFilmsArray.add(oneFilm)//грузим все фильмы подряд: и без ограничений 18+, и с ограничениями

                if (oneFilm.adult == false) {   //если isAdult == true, то грузим только с false
                    tempFilmsArray18free.add(oneFilm)
                }

            }
            filmsArray18free = tempFilmsArray18free
            filmsArray = tempFilmsArray

            if (isAdult == true) {
                return tempFilmsArray
            } else return tempFilmsArray18free

        } catch (e: Exception) {
            e.printStackTrace()
            return listOf()
        }
    }

    private fun getLinesForOld(reader: BufferedReader): String {
        val rawData = StringBuilder(2048)
        var tempVariable: String?
        while (reader.readLine().also { tempVariable = it } != null) {
            rawData.append(tempVariable).append("\n")
        }
        reader.close()
        return rawData.toString()
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun getLines(reader: BufferedReader): String {
        return reader.lines().collect(Collectors.joining("\n"))
    }
}