package com.example.jbbank.presentation.profile

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.core.domain.model.User
import com.example.jbbank.R
import com.example.jbbank.databinding.FragmentProfileBinding
import com.example.jbbank.framework.db.FirebaseHelper
import com.example.jbbank.util.StateView
import com.example.jbbank.util.showBottomSheet
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by João Bosco on 27/11/2023.
 */
@AndroidEntryPoint
class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private var _binding: FragmentProfileBinding? = null
    private val binding: FragmentProfileBinding get() = _binding!!

    private val profileViewModel: ProfileViewModel by viewModels()

    private var user: User? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentProfileBinding.bind(view)

        getProfile()
    }

    private fun getProfile() {
        profileViewModel.getProfile().observe(viewLifecycleOwner) { stateView ->
            when (stateView) {
                is StateView.Loading -> {
                    binding.progress.isVisible = true
                }

                is StateView.Success -> {
                    binding.progress.isVisible = false
                    stateView.data?.let { user = it }
                    configData()
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

    private fun configData() {
        binding.toolbarProfile.txtTitle.text = getString(R.string.text_title_toolbar_profile)
        binding.toolbarProfile.btnBack.ibBackWhite.setOnClickListener { findNavController().popBackStack() }
        binding.profileEditName.setText(user?.name)
        binding.profileEditPhone.setText(user?.phone)
        binding.profileEditEmail.setText(user?.email)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}