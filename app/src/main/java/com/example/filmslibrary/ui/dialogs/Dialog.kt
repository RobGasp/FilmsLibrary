package com.example.filmslibrary.ui.dialogs


import android.app.AlertDialog
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.filmslibrary.R
import com.example.filmslibrary.databinding.SiginDialogBinding
import com.example.filmslibrary.model.accountHelper.AccountHelper
import com.example.filmslibrary.model.accountHelper.FirebaseAuthentication

class Dialog(
    private val fragment: Fragment,
    onClosed:()-> Unit
) {
    private val accountHelper = AccountHelper.newInstance(fragment)
    val onClosed : ()->Unit = { onClosed() }
    fun createSignDialog(index: Int) {
        val builder = AlertDialog.Builder(fragment.requireContext())
        val binding = SiginDialogBinding.inflate(fragment.layoutInflater)
        val view = binding.root
        builder.setView(view)


        setDialogState(index, binding)

        val dialog = builder.create()
        binding.btnSignUpIn.setOnClickListener {
            setSignAction(index, dialog, binding)
        }

        binding.btnForgetPass.setOnClickListener {
            setResetPasswordAction(dialog, binding)
        }

        dialog.show()
        dialog.setOnDismissListener { onClosed() }

    }

    private fun setResetPasswordAction(dialog: AlertDialog?, binding: SiginDialogBinding) {
        val email = binding.signInEmail.text
        if (email.isNotEmpty()) {
            FirebaseAuthentication.mAuth.sendPasswordResetEmail(email.toString())
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        dialog?.dismiss()
                        Toast.makeText(
                            fragment.requireContext(),
                            fragment.resources.getString(R.string.email_reset_was_sent),
                            Toast.LENGTH_LONG
                        ).show()
                    } else {
                        Toast.makeText(
                            fragment.requireContext(),
                            fragment.requireContext().getString(R.string.error_password_recover),
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
        } else {
            binding.errorEmail.visibility = View.VISIBLE
        }
    }

    private fun setSignAction(index: Int, dialog: AlertDialog?, binding: SiginDialogBinding) {
        if (index == DialogConst.SIGN_UP_STATE) {
            if (binding.signInPassword.text.toString() == binding.signInPasswordRepeat.text.toString()) {
                binding.passwordErrorMessage.visibility = View.GONE
                accountHelper.signUpWithEmail(
                    binding.signInEmail.text.toString(),
                    binding.signInPassword.text.toString()
                ) { dialog?.dismiss() }
            } else {
                binding.passwordErrorMessage.visibility = View.VISIBLE
            }
        } else {
            accountHelper.signInWithEmail(
                binding.signInEmail.text.toString(),
                binding.signInPassword.text.toString()
            ) { dialog?.dismiss() }

        }
    }

    private fun setDialogState(index: Int, binding: SiginDialogBinding) {
        if (index == DialogConst.SIGN_UP_STATE) {
                binding.signInTittle.text = fragment.resources.getString(R.string.sign_up)
            binding.btnSignUpIn.text = fragment.resources.getString(R.string.sign_up_action)
            binding.signInPasswordRepeat.visibility = View.VISIBLE
            binding.btnForgetPass.visibility = View.GONE

        } else {
            binding.signInPasswordRepeat.visibility = View.GONE
            binding.passwordErrorMessage.visibility = View.GONE
            binding.btnForgetPass.visibility = View.VISIBLE
            binding.signInTittle.text = fragment.resources.getString(R.string.sign_in)
            binding.btnSignUpIn.text = fragment.resources.getString(R.string.sign_in_action)
        }
    }


}