package com.example.jbbank.presentation.register

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.core.domain.model.User
import com.example.core.domain.model.Wallet
import com.example.jbbank.R
import com.example.jbbank.databinding.FragmentRegisterBinding
import com.example.jbbank.framework.db.FirebaseHelper
import com.example.jbbank.presentation.profile.ProfileViewModel
import com.example.jbbank.presentation.wallet.WalletViewModel
import com.example.jbbank.util.BaseFragment
import com.example.jbbank.util.GetMask.PHONE_QUANTITY
import com.example.jbbank.util.StateView
import com.example.jbbank.util.showBottomSheet
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by João Bosco on 02/11/2023.
 */
@AndroidEntryPoint
class RegisterFragment : BaseFragment<FragmentRegisterBinding>(
    R.layout.fragment_register,
    FragmentRegisterBinding::bind
) {

    private val registerViewModel: RegisterViewModel by viewModels()
    private val profileViewModel: ProfileViewModel by viewModels()
    private val walletViewModel: WalletViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUi()
    }

    private fun initUi() {
            binding?.btnBack?.ibBack?.setOnClickListener { findNavController().popBackStack() }
            binding?.btnSignUp?.setOnClickListener { validData() }
    }

    @Suppress("NestedBlockDepth")
    private fun validData() {
        val name = binding?.registerEditName?.text.toString().trim()
        val email = binding?.registerEditEmail?.text.toString().trim()
        val phone = binding?.registerEditPhone?.unMaskedText
        val password = binding?.registerEditPassword?.text.toString().trim()

        if (name.isNotEmpty()) {
            if (email.isNotEmpty()) {
                if (phone?.isNotEmpty() == true) {
                    if (phone.length == PHONE_QUANTITY) {
                        if (password.isNotEmpty()) {

                            registerUser(name, email, phone, password)

                        } else {
                            binding?.registerEditPassword?.requestFocus()
                            showBottomSheet(message = getString(R.string.text_password_empty))
                        }
                    } else {
                        binding?.registerEditPhone?.requestFocus()
                        showBottomSheet(message = getString(R.string.text_phone_invalid))
                    }
                } else {
                    binding?.registerEditPhone?.requestFocus()
                    showBottomSheet(message = getString(R.string.text_phone_empty))
                }
            } else {
                binding?.registerEditEmail?.requestFocus()
                showBottomSheet(message = getString(R.string.text_email_empty))
            }
        } else {
            binding?.registerEditName?.requestFocus()
            showBottomSheet(message = getString(R.string.text_name_empty))
        }
    }

    private fun registerUser(name: String, email: String, phone: String, password: String) {
        registerViewModel.register(name, email, phone, password)
            .observe(viewLifecycleOwner) { stateView ->
                when (stateView) {
                    is StateView.Loading -> {
                        binding?.progress?.isVisible = true
                    }

                    is StateView.Success -> {
                        stateView.data?.let { saveProfile(it) }
                    }

                    is StateView.Error -> {
                        Log.e("FIREBASE_AUTH", stateView.message.toString())
                        binding?.progress?.isVisible = false
                        showBottomSheet(
                            message = getString(FirebaseHelper.validError(stateView.message.toString()))
                        )
                    }
                }
            }
    }

    private fun saveProfile(user: User) {
        profileViewModel.saveProfile(user).observe(viewLifecycleOwner) { stateView ->
            when (stateView) {
                is StateView.Loading -> {}
                is StateView.Success -> {
                    initWallet()
                }

                is StateView.Error -> {
                    binding?.progress?.isVisible = false
                    showBottomSheet(
                        message = getString(FirebaseHelper.validError(stateView.message.toString()))
                    )
                }
            }
        }
    }

    private fun initWallet() {
        walletViewModel.initWallet(
            Wallet(
                userId = FirebaseHelper.getUserId()
            )
        ).observe(viewLifecycleOwner) { stateView ->
            when (stateView) {
                is StateView.Loading -> {}
                is StateView.Success -> {
                    binding?.progress?.isVisible = false
                    findNavController().navigate(R.id.action_global_homeFragment)
                    view?.let {
                        Snackbar.make(
                            it, "Usuário cadastrado com sucesso!", Snackbar.LENGTH_SHORT
                        ).show()
                    }
                }

                is StateView.Error -> {
                    binding?.progress?.isVisible = false
                    showBottomSheet(
                        message = getString(FirebaseHelper.validError(stateView.message.toString()))
                    )
                }
            }
        }
    }
}