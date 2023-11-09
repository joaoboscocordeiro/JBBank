package com.example.jbbank.presentation.login

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.jbbank.R
import com.example.jbbank.databinding.FragmentLoginBinding
import com.example.jbbank.util.StateView
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by João Bosco on 02/11/2023.
 */
@AndroidEntryPoint
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding: FragmentLoginBinding get() = _binding!!

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentLoginBinding.inflate(inflater, container, false)
        .apply { _binding = this }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUi()
    }

    private fun initUi() {
        with(binding) {
            loginTxtForgotPassword.setOnClickListener {
                findNavController().navigate(R.id.action_loginFragment_to_recoveryFragment)
            }
            loginTxtRegister.setOnClickListener {
                findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
            }
            btnSignIn.setOnClickListener { validData() }
        }
    }

    private fun validData() {
        val email = binding.loginEditEmail.text.toString().trim()
        val password = binding.loginEditPassword.text.toString().trim()

        if (email.isNotEmpty()) {
            if (password.isNotEmpty()) {

                loginUser(email, password)

            } else {
                Toast.makeText(requireContext(), "Digite sua senha.", Toast.LENGTH_LONG).show()
            }
        } else {
            Toast.makeText(requireContext(), "Digite seu e-mail.", Toast.LENGTH_LONG).show()
        }
    }

    private fun loginUser(email: String, password: String) {
        viewModel.login(email, password).observe(viewLifecycleOwner) { stateView ->
            when (stateView) {
                is StateView.Loading -> {
                    binding.progress.isVisible = true
                }

                is StateView.Success -> {
                    binding.progress.isVisible = false
                    findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
                }

                is StateView.Error -> {
                    Log.e("FIREBASE_AUTH", stateView.message.toString())
                    binding.progress.isVisible = false
                    Toast.makeText(
                        requireContext(), stateView.message,
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}