package com.example.jbbank.presentation.features.deposit

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.core.domain.enum.TransactionOperation
import com.example.core.domain.enum.TransactionType
import com.example.core.domain.model.Deposit
import com.example.core.domain.model.Transaction
import com.example.jbbank.R
import com.example.jbbank.databinding.FragmentDepositFormBinding
import com.example.jbbank.util.BaseFragment
import com.example.jbbank.util.StateView
import com.example.jbbank.util.showBottomSheet
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by João Bosco on 11/11/2023.
 */
@AndroidEntryPoint
class DepositFormFragment : BaseFragment<FragmentDepositFormBinding>(
    R.layout.fragment_deposit_form,
    FragmentDepositFormBinding::bind
) {

    private val depositViewModel: DepositViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUi()
    }

    private fun initUi() {
        binding?.toolbarDeposit?.txtTitle?.text = getString(R.string.text_deposit)
        binding?.toolbarDeposit?.btnBack?.ibBack?.setOnClickListener { findNavController().popBackStack() }
        binding?.btnConfirm?.setOnClickListener { validateDeposit() }
    }

    private fun validateDeposit() {
        val amount = binding?.editAmount?.text.toString().trim()

        if (amount.isNotEmpty()) {
            val deposit = Deposit(amount = amount.toFloat())
            hideKeyboard()
            saveDeposit(deposit)
        } else {
            binding?.editAmount?.requestFocus()
            showBottomSheet(message = getString(R.string.text_message_empty))
        }
    }

    private fun saveDeposit(deposit: Deposit) {
        depositViewModel.saveDeposit(deposit).observe(viewLifecycleOwner) { stateView ->
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

    private fun saveTransaction(deposit: Deposit) {

        val transaction = Transaction(
            id = deposit.id,
            operation = TransactionOperation.DEPOSIT,
            date = deposit.date,
            amount = deposit.amount,
            type = TransactionType.CASH_IN
        )

        depositViewModel.saveTransaction(transaction).observe(viewLifecycleOwner) { stateView ->
            when (stateView) {
                is StateView.Loading -> {}

                is StateView.Success -> {
                    binding?.progress?.isVisible = false

                    val action = DepositFormFragmentDirections
                        .actionDepositFormFragmentToReceiptFragment(deposit.id)
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