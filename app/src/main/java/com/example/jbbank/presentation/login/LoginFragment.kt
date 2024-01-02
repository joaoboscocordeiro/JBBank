package com.example.jbbank.presentation.login

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.jbbank.R
import com.example.jbbank.databinding.FragmentLoginBinding
import com.example.jbbank.framework.network.FirebaseHelper
import com.example.jbbank.util.BaseFragment
import com.example.jbbank.util.StateView
import com.example.jbbank.util.showBottomSheet
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by João Bosco on 02/11/2023.
 */
@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>(
    R.layout.fragment_login,
    FragmentLoginBinding::bind
) {

    private val viewModel: LoginViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUi()
    }

    private fun initUi() {
        binding?.loginTxtForgotPassword?.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_recoveryFragment)
        }
        binding?.loginTxtRegister?.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
        binding?.btnSignIn?.setOnClickListener { validData() }
    }

    private fun validData() {
        val email = binding?.loginEditEmail?.text.toString().trim()
        val password = binding?.loginEditPassword?.text.toString().trim()

        if (email.isNotEmpty()) {
            if (password.isNotEmpty()) {

                loginUser(email, password)

            } else {
                binding?.loginEditPassword?.requestFocus()
                showBottomSheet(message = getString(R.string.text_password_empty))
            }
        } else {
            binding?.loginEditEmail?.requestFocus()
            showBottomSheet(message = getString(R.string.text_email_empty))
        }
    }

    private fun loginUser(email: String, password: String) {
        viewModel.login(email, password).observe(viewLifecycleOwner) { stateView ->
            when (stateView) {
                is StateView.Loading -> {
                    binding?.progress?.isVisible = true
                }

                is StateView.Success -> {
                    binding?.progress?.isVisible = false
                    findNavController().navigate(R.id.action_global_homeFragment)
                    view?.let {
                        Snackbar.make(
                            it, "Usuário logado com sucesso!",
                            Snackbar.LENGTH_SHORT
                        ).show()
                    }
                }

                is StateView.Error -> {
                    binding?.progress?.isVisible = false
                    Log.i("FIREBASE_AUTH", "loginUser: ${stateView.message}")
                    showBottomSheet(
                        message = getString(FirebaseHelper.validError(stateView.message ?: ""))
                    )
                }
            }
        }
    }
}