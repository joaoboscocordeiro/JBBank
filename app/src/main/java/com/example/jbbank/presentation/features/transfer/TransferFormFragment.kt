package com.example.jbbank.presentation.features.transfer

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.jbbank.R
import com.example.jbbank.databinding.FragmentTransferFormBinding
import com.example.jbbank.util.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by João Bosco on 10/07/2024.
 */
@AndroidEntryPoint
class TransferFormFragment : BaseFragment<FragmentTransferFormBinding>(
    R.layout.fragment_transfer_form,
    FragmentTransferFormBinding::bind
) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun initUi() {
        binding?.toolbarTransferForm?.txtTitle?.text = getString(R.string.text_transfer)
        binding?.toolbarTransferForm?.btnBack?.ibBack?.setOnClickListener { findNavController().popBackStack() }
        binding?.btnConfirm?.setOnClickListener { validData() }
    }

    private fun validData() {

    }
}