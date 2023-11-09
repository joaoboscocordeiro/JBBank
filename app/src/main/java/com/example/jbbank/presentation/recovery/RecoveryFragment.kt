package com.example.jbbank.presentation.recovery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.jbbank.R
import com.example.jbbank.databinding.FragmentRecoveryBinding
import com.example.jbbank.framework.db.FirebaseHelper
import com.example.jbbank.util.StateView
import com.example.jbbank.util.showBottomSheet
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by João Bosco on 02/11/2023.
 */
@AndroidEntryPoint
class RecoveryFragment : Fragment() {

    private var _binding: FragmentRecoveryBinding? = null
    private val binding: FragmentRecoveryBinding get() = _binding!!

    private val viewModel: RecoveryViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentRecoveryBinding.inflate(inflater, container, false)
        .apply { _binding = this }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUi()
    }

    private fun initUi() {
        with(binding) {
            btnRecovery.setOnClickListener { validData() }
        }
    }

    private fun validData() {
        val email = binding.recoveryEditEmail.text.toString().trim()
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
                    binding.progress.isVisible = true
                }

                is StateView.Success -> {
                    binding.progress.isVisible = false
                    findNavController().navigate(R.id.action_recoveryFragment_to_loginFragment)
                    Toast.makeText(
                        requireContext(),
                        "E-mail enviado com sucesso",
                        Toast.LENGTH_LONG
                    ).show()
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
}