package com.example.filmslibrary.model.firebaseDb

import android.util.Log
import com.example.filmslibrary.room.entity.FavoriteFilmEntity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class FirebaseDbManager {
    val db = Firebase.database.getReference("main")
    var fireBaseCallback: FireBaseCallback? = null
//    var list = mutableListOf<FavouriteFilmFirebase>()
    val auth = Firebase.auth
//    private val listFilms: MutableList<FavouriteFilmFirebase>? = null

    fun postToDb(filmListFromLocalDB: List<FavoriteFilmEntity>) {
        Log.d("TAG", auth.uid.toString())
        if (auth.uid != null) {
            Log.d("TAG", "in")
            for (item in filmListFromLocalDB) {
                db.child(auth.uid.toString()).child(item.id.toString()).child("FavoriteFilmEntity")
                    .setValue(item)
                Log.d("TAG",item.id.toString())
            }
        }
    }



    fun getFromDb() {
        db.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val listFilms: MutableList<FavouriteFilmFirebase>? = mutableListOf()
                val result = snapshot.child(auth.uid.toString())
                for (item in result.children) {
                    val film =
                        item.child("FavoriteFilmEntity").getValue(FavouriteFilmFirebase::class.java)
                    Log.d("TAGG", film.toString())
                   if (film != null) {
                       listFilms?.add(film)
                       Log.d("TAGG", film.toString() + "added")
                   }
                }
                Log.d("TAGG", "list" + listFilms.toString())
                if (listFilms != null) {
                    fireBaseCallback?.getData(listFilms)
                    Log.d("TAGG", listFilms.toString())
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

    }



   fun interface FireBaseCallback {
        fun getData(list: MutableList<FavouriteFilmFirebase>)
    }
}