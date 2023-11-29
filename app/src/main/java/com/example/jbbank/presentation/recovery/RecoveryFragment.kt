package com.example.jbbank.presentation.recovery

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.jbbank.R
import com.example.jbbank.databinding.FragmentRecoveryBinding
import com.example.jbbank.framework.db.FirebaseHelper
import com.example.jbbank.util.BaseFragment
import com.example.jbbank.util.StateView
import com.example.jbbank.util.showBottomSheet
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by João Bosco on 02/11/2023.
 */
@AndroidEntryPoint
class RecoveryFragment : BaseFragment<FragmentRecoveryBinding>(
    R.layout.fragment_recovery,
    FragmentRecoveryBinding::bind
) {
    private val viewModel: RecoveryViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUi()
    }

    private fun initUi() {
            binding?.btnBack?.ibBack?.setOnClickListener { findNavController().popBackStack() }
            binding?.btnRecovery?.setOnClickListener { validData() }
    }

    private fun validData() {
        val email = binding?.recoveryEditEmail?.text.toString().trim()
        if (email.isNotEmpty()) {
            recoveryAccount(email)
        } else {
            showBottomSheet(message = getString(R.string.text_email_empty))
        }
    }

    private fun recoveryAccount(email: String) {
        viewModel.recovery(email).observe(viewLifecycleOwner) { stateView ->
            when (stateView) {
                is StateView.Loading -> {
                    binding?.progress?.isVisible = true
                }

                is StateView.Success -> {
                    binding?.progress?.isVisible = false
                    findNavController().navigate(R.id.action_recoveryFragment_to_loginFragment)
                    view?.let {
                        Snackbar.make(
                            it, "E-mail enviado com sucesso!", Snackbar.LENGTH_SHORT
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