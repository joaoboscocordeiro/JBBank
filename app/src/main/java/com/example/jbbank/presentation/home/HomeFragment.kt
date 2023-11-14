package com.example.jbbank.presentation.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.core.domain.enum.TransactionType
import com.example.core.domain.model.Transaction
import com.example.jbbank.R
import com.example.jbbank.databinding.FragmentHomeBinding
import com.example.jbbank.util.GetMask
import com.example.jbbank.util.StateView
import com.example.jbbank.util.showBottomSheet
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by João Bosco on 07/11/2023.
 */
@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding get() = _binding!!

    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentHomeBinding.inflate(inflater, container, false)
        .apply { _binding = this }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUi()
        getTransactions()
    }

    private fun initUi() {
        with(binding) {
            cardDepositHome.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_depositFormFragment)
            }
        }
    }

    private fun getTransactions() {
        homeViewModel.getTransactions().observe(viewLifecycleOwner) { stateView ->
            when (stateView) {
                is StateView.Loading -> {
                }

                is StateView.Success -> {
                    showBalance(stateView.data ?: emptyList())
                }

                is StateView.Error -> {
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

        binding.textBalanceHome.text =
            getString(R.string.text_format_value, GetMask.getFormatValue(cashIn - cashOut))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}