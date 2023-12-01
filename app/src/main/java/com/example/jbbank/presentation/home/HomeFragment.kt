package com.example.jbbank.presentation.home

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.core.domain.enum.TransactionOperation
import com.example.core.domain.enum.TransactionType
import com.example.core.domain.model.Transaction
import com.example.jbbank.R
import com.example.jbbank.databinding.FragmentHomeBinding
import com.example.jbbank.framework.db.FirebaseHelper
import com.example.jbbank.util.BaseFragment
import com.example.jbbank.util.GetMask
import com.example.jbbank.util.GetMask.NUMBER_TAKE
import com.example.jbbank.util.StateView
import com.example.jbbank.util.showBottomSheet
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by João Bosco on 07/11/2023.
 */
@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(
    R.layout.fragment_home,
    FragmentHomeBinding::bind
) {

    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var adapter: TransactionAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUi()
        getTransactions()
    }

    private fun initUi() {
        adapter = TransactionAdapter(requireContext()) { transaction ->
            when (transaction.operation) {
                TransactionOperation.DEPOSIT -> {
                    val action = HomeFragmentDirections
                        .actionHomeFragmentToReceiptFragment(transaction.id)
                    findNavController().navigate(action)
                }

                else -> {

                }
            }
        }

        binding?.cardDepositHome?.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_depositFormFragment)
        }
        binding?.cardExtractHome?.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_extractFragment)
        }
        binding?.cardProfileHome?.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_profileFragment)
        }
        binding?.cardRechargeHome?.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_rechargeReceiptFragment)
        }
        binding?.textHomeShowAll?.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_extractFragment)
        }

        binding?.btnLogout?.setOnClickListener {
            FirebaseHelper.getAuth().signOut()
            findNavController().navigate(R.id.action_homeFragment_to_navigation)
        }

        binding?.rvTransaction?.setHasFixedSize(true)
        binding?.rvTransaction?.adapter = adapter
    }

    private fun getTransactions() {
        homeViewModel.getTransactions().observe(viewLifecycleOwner) { stateView ->
            when (stateView) {
                is StateView.Loading -> {
                    binding?.progress?.isVisible = true
                }

                is StateView.Success -> {
                    binding?.progress?.isVisible = false

                    adapter.submitList(stateView.data?.reversed()?.take(NUMBER_TAKE))

                    showBalance(stateView.data ?: emptyList())
                }

                is StateView.Error -> {
                    binding?.progress?.isVisible = false
                    Log.i("WALLET", "loginUser: ${stateView.message}")
                    showBottomSheet(message = stateView.message)
                }
            }
        }
    }

    private fun showBalance(transactions: List<Transaction>) {
        var cashIn = 0f
        var cashOut = 0f

        transactions.forEach { transaction ->
            if (transaction.type == TransactionType.CASH_IN) {
                cashIn += transaction.amount
            } else {
                cashOut += transaction.amount
            }
        }

        binding?.textBalanceHome?.text =
            getString(R.string.text_format_value, GetMask.getFormatValue(cashIn - cashOut))
    }
}