package com.example.jbbank.presentation.features.transfer

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.jbbank.R
import com.example.jbbank.databinding.FragmentTransferUserListBinding
import com.example.jbbank.util.BaseFragment
import com.example.jbbank.util.StateView
import com.example.jbbank.util.showBottomSheet
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by João Bosco on 07/12/2023.
 */
@AndroidEntryPoint
class TransferUserListFragment : BaseFragment<FragmentTransferUserListBinding>(
    R.layout.fragment_transfer_user_list,
    FragmentTransferUserListBinding::bind
) {

    private lateinit var transferUserAdapter: TransferUserAdapter

    private val transferUserListViewModel: TransferUserListViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUi()
        getProfileList()
    }

    private fun initUi() {
        binding?.toolbarUsers?.txtTitle?.text = getString(R.string.text_title_users)
        binding?.toolbarUsers?.btnBack?.ibBack?.setOnClickListener { findNavController().popBackStack() }

        transferUserAdapter = TransferUserAdapter { userSelected ->
            Toast.makeText(requireContext(), userSelected.name, Toast.LENGTH_SHORT).show()
        }

        with(binding?.rvUsers) {
            this?.setHasFixedSize(true)
            this?.adapter = transferUserAdapter
        }
    }

    private fun getProfileList() {
        transferUserListViewModel.getProfileList().observe(viewLifecycleOwner) { stateView ->
            when (stateView) {
                is StateView.Loading -> {
                    binding?.progress?.isVisible = true
                }

                is StateView.Success -> {
                    transferUserAdapter.submitList(stateView.data)
                    binding?.progress?.isVisible = false
                }

                is StateView.Error -> {
                    binding?.progress?.isVisible = false
                    showBottomSheet(message = stateView.message)
                }
            }
        }
    }
}