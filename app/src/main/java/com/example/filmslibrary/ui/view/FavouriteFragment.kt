package com.example.filmslibrary.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.filmslibrary.R
import com.example.filmslibrary.databinding.FragmentFavouriteBinding
import com.example.filmslibrary.model.accountHelper.FirebaseAuthentication
import com.example.filmslibrary.model.repository.FilmObject
import com.example.filmslibrary.ui.dialogs.Dialog
import com.example.filmslibrary.ui.dialogs.DialogConst
import com.google.firebase.auth.FirebaseUser

class FavouriteFragment : Fragment(), FragmentContract {

    private var _binding: FragmentFavouriteBinding? = null
    private val binding get() = _binding!!

    private var dialog: Dialog? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentFavouriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        update(FirebaseAuthentication.mAuth.currentUser)

        binding.textToDelete.setOnClickListener {

            //болванка фильма
            val movie = FilmObject(title = "Movie from Favourite Fragment")

            //Эти две строки для открытия нового фргамента с описанием фильма, поместить потом в адаптер
            // в клик листенер для элемента списка
            //не забудьте передать в него обьект фильма выбранного, пока я сделал на существующий DTO класс,
            //как помеянете класс, я изменю его.
            val action =
                FavouriteFragmentDirections.actionFavouriteFragmentToDetailsPageFragment(movie = movie)
            view.findNavController().navigate(action)

        }
        dialog = Dialog(this)
        binding.btnAuth.setOnClickListener {
            dialog?.createSignDialog(DialogConst.SIGN_UP_STATE)
            update(FirebaseAuthentication.mAuth.currentUser)
        }
        binding.btnSignIn.setOnClickListener {
            dialog?.createSignDialog(DialogConst.SIGN_IN_STATE)
            update(FirebaseAuthentication.mAuth.currentUser)
        }
    }


    override fun onStart() {
        super.onStart()
        update(FirebaseAuthentication.mAuth.currentUser)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    override fun update(user: FirebaseUser?) {
        if (user == null) {
                binding.btnSignIn.visibility = View.VISIBLE
                binding.btnAuth.visibility = View.VISIBLE

        } else {
                binding.btnSignIn.visibility = View.GONE
                binding.btnAuth.visibility = View.GONE

        }
    }
}