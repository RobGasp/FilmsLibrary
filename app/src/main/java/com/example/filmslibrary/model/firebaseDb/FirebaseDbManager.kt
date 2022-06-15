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
    val auth = Firebase.auth

    fun postToDb(filmListFromLocalDB: List<FavoriteFilmEntity>) {
        if (auth.uid != null) {
            for (item in filmListFromLocalDB) {
                db.child(auth.uid.toString()).child(item.id.toString()).child("FavoriteFilmEntity")
                    .setValue(item)
            }
        }
    }

    fun postFilmToDb(filmListFromLocalDB: FavoriteFilmEntity) {
        if (auth.uid != null) {
            db.child(auth.uid.toString()).child(filmListFromLocalDB.id.toString())
                .child("FavoriteFilmEntity")
                .setValue(filmListFromLocalDB)
        }
    }

    fun deleteFilmFromDb(filmListFromLocalDB: FavoriteFilmEntity) {
        if (auth.uid != null) {
            db.child(auth.uid.toString()).child(filmListFromLocalDB.id.toString()).removeValue()
        }
    }


    fun getFromDb(fireBaseCallback: FireBaseCallback) {
        db.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val listFilms: MutableList<FavouriteFilmFirebase> = mutableListOf()
                val result = snapshot.child(auth.uid.toString())
                for (item in result.children) {
                    val film =
                        item.child("FavoriteFilmEntity").getValue(FavouriteFilmFirebase::class.java)

                    if (film != null) {
                        listFilms.add(film)
                    }
                }
                fireBaseCallback.getData(listFilms)
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

    }


    fun interface FireBaseCallback {
        fun getData(list: MutableList<FavouriteFilmFirebase>)
    }

    fun interface DeleteListener {
        fun getData(list: MutableList<FavouriteFilmFirebase>)
    }
}