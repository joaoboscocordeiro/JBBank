package com.example.jbbank.presentation.features.recharge

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.core.domain.enum.TransactionOperation
import com.example.core.domain.enum.TransactionType
import com.example.core.domain.model.Recharge
import com.example.core.domain.model.Transaction
import com.example.jbbank.R
import com.example.jbbank.databinding.FragmentRechargeFormBinding
import com.example.jbbank.util.BaseFragment
import com.example.jbbank.util.GetMask.PHONE_QUANTITY
import com.example.jbbank.util.StateView
import com.example.jbbank.util.showBottomSheet
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by João Bosco on 01/12/2023.
 */
@AndroidEntryPoint
class RechargeFormFragment : BaseFragment<FragmentRechargeFormBinding>(
    R.layout.fragment_recharge_form,
    FragmentRechargeFormBinding::bind
) {

    private val rechargeViewModel: RechargeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUi()
    }

    private fun initUi() {
        binding?.toolbarRechargeForm?.txtTitle?.text = getString(R.string.text_recharge)
        binding?.toolbarRechargeForm?.btnBack?.ibBack?.setOnClickListener { findNavController().popBackStack() }
        binding?.btnConfirm?.setOnClickListener { validData() }
    }

    private fun validData() {
        val amount = binding?.editAmountRecharge?.text.toString().trim()
        val phone = binding?.editPhoneNumberRecharge?.unMaskedText

        if (amount.isNotEmpty()) {
            if (phone?.isNotEmpty() == true) {
                if (phone.length == PHONE_QUANTITY) {

                    hideKeyboard()

                    val recharge = Recharge(
                        amount = amount.toFloat(),
                        number = phone
                    )
                    saveRecharge(recharge)

                } else {
                    binding?.editPhoneNumberRecharge?.requestFocus()
                    showBottomSheet(message = getString(R.string.text_phone_invalid))
                }
            } else {
                binding?.editPhoneNumberRecharge?.requestFocus()
                showBottomSheet(message = getString(R.string.text_phone_empty))
            }
        } else {
            binding?.editAmountRecharge?.requestFocus()
            showBottomSheet(message = getString(R.string.text_amount_empty))
        }
    }

    private fun saveRecharge(recharge: Recharge) {
        rechargeViewModel.saveRecharge(recharge).observe(viewLifecycleOwner) { stateView ->
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

    private fun saveTransaction(recharge: Recharge) {

        val transaction = Transaction(
            id = recharge.id,
            operation = TransactionOperation.RECHARGE,
            date = recharge.date,
            amount = recharge.amount,
            type = TransactionType.CASH_OUT
        )

        rechargeViewModel.saveTransaction(transaction).observe(viewLifecycleOwner) { stateView ->
            when (stateView) {
                is StateView.Loading -> {}

                is StateView.Success -> {
                    binding?.progress?.isVisible = false

                    val action = RechargeFormFragmentDirections
                        .actionRechargeFormFragmentToRechargeReceiptFragment(recharge.id)
                    findNavController().navigate(action)

                    view?.let {
                        Snackbar.make(
                            it, "Transação com sucesso!", Snackbar.LENGTH_SHORT
                        ).show()
                    }
                }

                is StateView.Error -> {
                    binding?.progress?.isVisible = false
                    showBottomSheet(message = stateView.message)
                }
            }
        }
    }
}