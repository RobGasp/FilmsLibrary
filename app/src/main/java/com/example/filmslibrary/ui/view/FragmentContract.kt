package com.example.filmslibrary.ui.view

import com.google.firebase.auth.FirebaseUser

interface FragmentContract {
    fun update(user: FirebaseUser?)
}