package com.example.jbbank.presentation.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.core.domain.model.User
import com.example.jbbank.databinding.FragmentRegisterBinding
import com.example.jbbank.util.StateView
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by João Bosco on 02/11/2023.
 */
@AndroidEntryPoint
class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding: FragmentRegisterBinding get() = _binding!!

    private val viewModel: RegisterViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentRegisterBinding.inflate(inflater, container, false)
        .apply { _binding = this }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListener()
    }

    private fun initListener() {
        with(binding) {
            toolbar.btnBack.ibBack.setOnClickListener { }
            toolbar.txtTitle.text = "Criar Conta"
            btnRegister.setOnClickListener { validData() }
        }
    }

    @Suppress("NestedBlockDepth")
    private fun validData() {
        val name = binding.registerEditName.text.toString().trim()
        val email = binding.registerEditEmail.text.toString().trim()
        val phone = binding.registerEditPhone.text.toString().trim()
        val password = binding.registerEditPassword.text.toString().trim()

        if (name.isNotEmpty()) {
            if (email.isNotEmpty()) {
                if (phone.isNotEmpty()) {
                    if (password.isNotEmpty()) {

                        val user = User(name, email, phone, password)
                        registerUser(user)

                    } else {
                        Toast.makeText(requireContext(), "Digite sua senha.", Toast.LENGTH_LONG)
                            .show()
                    }
                } else {
                    Toast.makeText(requireContext(), "Digite seu telefone.", Toast.LENGTH_LONG)
                        .show()
                }
            } else {
                Toast.makeText(requireContext(), "Digite seu e-mail.", Toast.LENGTH_LONG).show()
            }
        } else {
            Toast.makeText(requireContext(), "Digite seu nome.", Toast.LENGTH_LONG).show()
        }
    }

    private fun registerUser(user: User) {
        viewModel.register(user).observe(viewLifecycleOwner) { stateView ->
            when (stateView) {
                is StateView.Loading -> {
                    binding.progress.isVisible = true
                }

                is StateView.Success -> {
                    binding.progress.isVisible = false
                    Toast.makeText(
                        requireContext(),
                        "Usuário registrado com sucesso.",
                        Toast.LENGTH_LONG
                    ).show()
                }

                is StateView.Error -> {
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