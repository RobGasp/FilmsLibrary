package com.example.filmslibrary.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.filmslibrary.R

import com.example.filmslibrary.databinding.FragmentSettingsBinding
import com.example.filmslibrary.model.accountHelper.FirebaseAuthentication
import com.example.filmslibrary.ui.dialogs.Dialog
import com.example.filmslibrary.ui.dialogs.DialogConst
import com.google.firebase.auth.FirebaseUser


class SettingsFragment : Fragment(), FragmentContract {

    private lateinit var textViewAccount: TextView
    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!
    private var dialog: Dialog? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        update(FirebaseAuthentication.mAuth.currentUser)
        textViewAccount = binding.accountName

        dialog = Dialog(this)

        binding.settingsSignIn.setOnClickListener {
            dialog?.createSignDialog(DialogConst.SIGN_IN_STATE)
            update(FirebaseAuthentication.mAuth.currentUser)
        }

        binding.settingsSignUp.setOnClickListener {
            dialog?.createSignDialog(DialogConst.SIGN_UP_STATE)
            update(FirebaseAuthentication.mAuth.currentUser)

        }

        binding.signOut.setOnClickListener {
            update(null)
            FirebaseAuthentication.signOut()

        }
    }


    override fun update(user: FirebaseUser?) {
        if (user == null) {
            binding.accountName.text = getString(R.string.not_registred)
            binding.signOut.visibility = View.GONE
            binding.settingsSignUp.visibility = View.VISIBLE
            binding.settingsSignIn.visibility = View.VISIBLE

        } else {

            binding.settingsSignUp.visibility = View.GONE
            binding.settingsSignIn.visibility = View.GONE
            binding.signOut.visibility = View.VISIBLE
            binding.accountName.text = user.email
        }
    }

    override fun onStart() {
        super.onStart()
        update(FirebaseAuthentication.mAuth.currentUser)
    }

}