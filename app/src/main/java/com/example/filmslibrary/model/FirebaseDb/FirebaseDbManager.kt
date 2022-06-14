package com.example.filmslibrary.model.FirebaseDb

import com.example.filmslibrary.room.entity.FavoriteFilmEntity
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class FirebaseDbManager {
    val db = Firebase.database.getReference("main")

    fun postToDb(filmListFromLocalDB: List<FavoriteFilmEntity>) {
        for (item  in filmListFromLocalDB){
            db.child(item.id.toString()).setValue(item)
        }
    }
}