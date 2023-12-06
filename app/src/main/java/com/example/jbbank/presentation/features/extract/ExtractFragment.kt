package com.example.jbbank.presentation.features.extract

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.core.domain.enum.TransactionOperation
import com.example.jbbank.R
import com.example.jbbank.databinding.FragmentExtractBinding
import com.example.jbbank.presentation.home.TransactionAdapter
import com.example.jbbank.util.BaseFragment
import com.example.jbbank.util.StateView
import com.example.jbbank.util.showBottomSheet
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by João Bosco on 22/11/2023.
 */
@AndroidEntryPoint
class ExtractFragment : BaseFragment<FragmentExtractBinding>(
    R.layout.fragment_extract,
    FragmentExtractBinding::bind
) {

    private lateinit var adapter: TransactionAdapter

    private val extractViewModel: ExtractViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUi()
        getTransactions()
    }

    private fun initUi() {
        adapter = TransactionAdapter(requireContext()) { transaction ->
            when (transaction.operation) {
                TransactionOperation.DEPOSIT -> {
                    val action = ExtractFragmentDirections
                        .actionExtractFragmentToReceiptFragment(transaction.id)
                    findNavController().navigate(action)
                }

                TransactionOperation.RECHARGE -> {
                    val action = ExtractFragmentDirections
                        .actionExtractFragmentToRechargeReceiptFragment(transaction.id)
                    findNavController().navigate(action)
                }

                else -> {
                }
            }
        }
        binding?.toolbarExtract?.txtTitle?.text = getString(R.string.text_title_toolbar_receipt)
        binding?.toolbarExtract?.btnBack?.ibBackWhite?.setOnClickListener { findNavController().popBackStack() }

        binding?.rvExtract?.setHasFixedSize(true)
        binding?.rvExtract?.adapter = adapter
    }

    private fun getTransactions() {
        extractViewModel.getExtract().observe(viewLifecycleOwner) { stateView ->
            when (stateView) {
                is StateView.Loading -> {
                    binding?.progress?.isVisible = true
                }

                is StateView.Success -> {
                    binding?.progress?.isVisible = false

                    adapter.submitList(stateView.data?.reversed())
                }

                is StateView.Error -> {
                    binding?.progress?.isVisible = false
                    Log.i("WALLET", "loginUser: ${stateView.message}")
                    showBottomSheet(message = stateView.message)
                }
            }
        }
    }
}