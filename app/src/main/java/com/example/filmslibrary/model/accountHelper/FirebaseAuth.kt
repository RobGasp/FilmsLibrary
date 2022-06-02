package com.example.filmslibrary.model.accountHelper

import com.google.firebase.auth.FirebaseAuth

object FirebaseAuthentication {
    val mAuth = FirebaseAuth.getInstance()
    fun signOut(){
        mAuth.signOut()
    }
}