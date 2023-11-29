package com.example.jbbank.presentation.profile

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.core.domain.model.User
import com.example.jbbank.R
import com.example.jbbank.databinding.FragmentProfileBinding
import com.example.jbbank.framework.db.FirebaseHelper
import com.example.jbbank.util.BaseFragment
import com.example.jbbank.util.GetMask.PHONE_QUANTITY
import com.example.jbbank.util.StateView
import com.example.jbbank.util.showBottomSheet
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by João Bosco on 27/11/2023.
 */
@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding>(
    R.layout.fragment_profile,
    FragmentProfileBinding::bind
) {

    private val profileViewModel: ProfileViewModel by viewModels()

    private var user: User? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getProfile()
    }

    private fun saveProfile() {
        user?.let { user ->
            profileViewModel.saveProfile(user).observe(viewLifecycleOwner) { stateView ->
                when (stateView) {
                    is StateView.Loading -> {
                        binding?.progress?.isVisible = true
                    }

                    is StateView.Success -> {
                        binding?.progress?.isVisible = false
                        Toast.makeText(
                            requireContext(),
                            getString(R.string.text_profile_save_success),
                            Toast.LENGTH_SHORT
                        ).show()
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

    private fun getProfile() {
        profileViewModel.getProfile().observe(viewLifecycleOwner) { stateView ->
            when (stateView) {
                is StateView.Loading -> {
                    binding?.progress?.isVisible = true
                }

                is StateView.Success -> {
                    binding?.progress?.isVisible = false
                    stateView.data?.let { user = it }
                    configData()
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

    private fun configData() {
        binding?.toolbarProfile?.txtTitle?.text = getString(R.string.text_title_toolbar_profile)
        binding?.toolbarProfile?.btnBack?.ibBackWhite?.setOnClickListener { findNavController().popBackStack() }

        binding?.profileEditName?.setText(user?.name)
        binding?.profileEditPhone?.setText(user?.phone)
        binding?.profileEditEmail?.setText(user?.email)

        binding?.btnSave?.setOnClickListener { if (user != null) validData() }
    }

    private fun validData() {
        val name = binding?.profileEditName?.text.toString().trim()
        val phone = binding?.profileEditPhone?.unMaskedText

        if (name.isNotEmpty()) {
            if (phone?.isNotEmpty() == true) {
                if (phone.length == PHONE_QUANTITY) {

                    hideKeyboard()

                    user?.name = name
                    user?.phone = phone

                    saveProfile()

                } else {
                    binding?.profileEditPhone?.requestFocus()
                    showBottomSheet(message = getString(R.string.text_phone_invalid))
                }
            } else {
                binding?.profileEditPhone?.requestFocus()
                showBottomSheet(message = getString(R.string.text_phone_empty))
            }
        } else {
            binding?.profileEditName?.requestFocus()
            showBottomSheet(message = getString(R.string.text_name_empty))
        }
    }
}