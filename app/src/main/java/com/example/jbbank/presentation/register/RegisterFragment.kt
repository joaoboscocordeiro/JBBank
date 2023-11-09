package com.example.jbbank.presentation.register

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.core.domain.model.User
import com.example.core.domain.model.Wallet
import com.example.jbbank.R
import com.example.jbbank.databinding.FragmentRegisterBinding
import com.example.jbbank.framework.db.FirebaseHelper
import com.example.jbbank.presentation.profile.ProfileViewModel
import com.example.jbbank.presentation.wallet.WalletViewModel
import com.example.jbbank.util.StateView
import com.example.jbbank.util.showBottomSheet
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by João Bosco on 02/11/2023.
 */
@AndroidEntryPoint
class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding: FragmentRegisterBinding get() = _binding!!

    private val registerViewModel: RegisterViewModel by viewModels()
    private val profileViewModel: ProfileViewModel by viewModels()
    private val walletViewModel: WalletViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentRegisterBinding.inflate(inflater, container, false)
        .apply { _binding = this }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUi()
    }

    private fun initUi() {
        with(binding) {
            btnSignUp.setOnClickListener { validData() }
        }
    }

    @Suppress("NestedBlockDepth")
    private fun validData() {
        val name = binding.registerEditName.text.toString().trim()
        val email = binding.registerEditEmail.text.toString().trim()
        val phone = binding.registerEditPhone.unMaskedText
        val password = binding.registerEditPassword.text.toString().trim()

        if (name.isNotEmpty()) {
            if (email.isNotEmpty()) {
                if (phone?.isNotEmpty() == true) {
                    if (phone.length == PHONE_QUANTITY) {
                        if (password.isNotEmpty()) {

                            registerUser(name, email, phone, password)

                        } else {
                            showBottomSheet(message = getString(R.string.text_password_empty))
                        }
                    } else {
                        showBottomSheet(message = getString(R.string.text_phone_invalid))
                    }
                } else {
                    showBottomSheet(message = getString(R.string.text_phone_empty))
                }
            } else {
                showBottomSheet(message = getString(R.string.text_email_empty))
            }
        } else {
            showBottomSheet(message = getString(R.string.text_name_empty))
        }
    }

    private fun registerUser(name: String, email: String, phone: String, password: String) {
        registerViewModel.register(name, email, phone, password)
            .observe(viewLifecycleOwner) { stateView ->
                when (stateView) {
                    is StateView.Loading -> {
                        binding.progress.isVisible = true
                    }

                    is StateView.Success -> {
                        stateView.data?.let { saveProfile(it) }
                    }

                    is StateView.Error -> {
                        Log.e("FIREBASE_AUTH", stateView.message.toString())
                        binding.progress.isVisible = false
                        showBottomSheet(
                            message = getString(FirebaseHelper.validError(stateView.message.toString()))
                        )
                    }
                }
            }
    }

    private fun saveProfile(user: User) {
        profileViewModel.save(user).observe(viewLifecycleOwner) { stateView ->
            when (stateView) {
                is StateView.Loading -> {}
                is StateView.Success -> {
                    initWallet()
                }

                is StateView.Error -> {
                    binding.progress.isVisible = false
                    showBottomSheet(
                        message = getString(FirebaseHelper.validError(stateView.message.toString()))
                    )
                }
            }
        }
    }

    private fun initWallet() {
        walletViewModel.initWallet(Wallet(
            userId = FirebaseHelper.getUserId()
        )).observe(viewLifecycleOwner) { stateView ->
            when (stateView) {
                is StateView.Loading -> {}
                is StateView.Success -> {
                    binding.progress.isVisible = false
                    findNavController().navigate(R.id.action_global_homeFragment)
                }

                is StateView.Error -> {
                    binding.progress.isVisible = false
                    showBottomSheet(
                        message = getString(FirebaseHelper.validError(stateView.message.toString()))
                    )
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val PHONE_QUANTITY = 11
    }
}