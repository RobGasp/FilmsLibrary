package com.example.filmslibrary.model.accountHelper


import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.filmslibrary.R
import com.example.filmslibrary.ui.view.FragmentContract

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class AccountHelper private constructor(
    fragment: Fragment,
) {

    private var user: FirebaseUser? = null
    private val fragment = fragment
    private val mAuth = FirebaseAuthentication.mAuth


    fun signUpWithEmail(email: String, password: String,dialogDismiss: () -> Unit) {
        if (email.isNotEmpty() && password.isNotEmpty()) {
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    task.result.user?.let { sendEmailVerification(it) }
                    user = task.result.user
                    val fragmentUpdater = fragment as FragmentContract
                    fragmentUpdater.update(user)
                    dialogDismiss()
                } else {
                    Toast.makeText(
                        fragment.requireActivity(),
                        fragment.resources.getString(R.string.sign_up_error),
                        Toast.LENGTH_LONG
                    )
                        .show()
                }
            }
        }
    }

    fun signInWithEmail(email: String, password: String,  dialogDismiss: () -> Unit) {
        if (email.isNotEmpty() && password.isNotEmpty()) {
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    user = task.result.user
                    val fragmentUpdater = fragment as FragmentContract
                    fragmentUpdater.update(user)
                    dialogDismiss()
                } else {

                    Toast.makeText(
                        fragment.requireActivity(),
                        fragment.resources.getString(R.string.sign_in_error),
                        Toast.LENGTH_LONG
                    )
                        .show()
                }
            }
        }
    }


    private fun sendEmailVerification(user: FirebaseUser) {
        user.sendEmailVerification().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Toast.makeText(
                    fragment.requireActivity(),
                    fragment.resources.getString(R.string.send_verification_email_successful),
                    Toast.LENGTH_LONG
                )
                    .show()
            } else {
                Toast.makeText(
                    fragment.requireActivity(),
                    fragment.resources.getString(R.string.send_verification_email_error),
                    Toast.LENGTH_LONG
                )
                    .show()
            }
        }
    }


    companion object {
        @Volatile
        private var INSTANCE: AccountHelper? = null
        fun newInstance(fragment: Fragment): AccountHelper = INSTANCE ?: synchronized(this) {
            INSTANCE
                ?: AccountHelper(fragment).also { INSTANCE = it }
        }

    }


}