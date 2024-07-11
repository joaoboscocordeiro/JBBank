package com.example.jbbank.presentation.features.transfer

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.example.jbbank.R
import com.example.jbbank.databinding.FragmentTransferFormBinding
import com.example.jbbank.presentation.features.recharge.RechargeViewModel
import com.example.jbbank.util.BaseFragment
import com.example.jbbank.util.StateView
import com.example.jbbank.util.showBottomSheet
import dagger.hilt.android.AndroidEntryPoint
import org.cristovolta.core.domain.model.Recharge

/**
 * Created by João Bosco on 10/07/2024.
 */
@AndroidEntryPoint
class TransferFormFragment : BaseFragment<FragmentTransferFormBinding>(
    R.layout.fragment_transfer_form,
    FragmentTransferFormBinding::bind
) {

    private val transferViewModel: TransferViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUi()
    }

    private fun initUi() {
        binding?.toolbarTransferForm?.txtTitle?.text = getString(R.string.text_transfer)
        binding?.toolbarTransferForm?.btnBack?.ibBack?.setOnClickListener { findNavController().popBackStack() }
        binding?.btnConfirm?.setOnClickListener { validData() }
    }

    private fun validData() {
        val amount = binding?.editAmountTransfer?.text.toString().trim()

        if (amount.isNotEmpty()) {

            hideKeyboard()

            saveTransfer(amount)

        } else {
            binding?.editAmountTransfer?.requestFocus()
            showBottomSheet(message = getString(R.string.text_amount_empty))
        }
    }

    private fun saveTransfer(transfer: String) {
        transferViewModel.saveTransfer(transfer).observe(viewLifecycleOwner) { stateView ->
            when (stateView) {
                is StateView.Loading -> {
                    binding?.progress?.isVisible = true
                }

                is StateView.Success -> {
                    binding?.progress?.isVisible = false
                    stateView.data?.let { saveTransaction(it) }
                }

                is StateView.Error -> {
                    binding?.progress?.isVisible = false
                    showBottomSheet(message = stateView.message)
                }
            }
        }
    }
}