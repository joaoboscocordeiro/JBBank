package com.example.jbbank.presentation.features.deposit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.core.domain.model.Deposit
import com.example.jbbank.R
import com.example.jbbank.databinding.FragmentReceiptBinding
import com.example.jbbank.util.GetMask
import com.example.jbbank.util.GetMask.DAY_MONTH_YEAR_HOUR_MINUTE
import com.example.jbbank.util.StateView
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by João Bosco on 12/11/2023.
 */
@AndroidEntryPoint
class ReceiptFragment : Fragment() {

    private var _binding: FragmentReceiptBinding? = null
    private val binding: FragmentReceiptBinding get() = _binding!!

    private val viewModel: DepositReceiptViewModel by viewModels()

    private val args: ReceiptFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentReceiptBinding.inflate(inflater, container, false)
        .apply { _binding = this }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getDeposit()
    }

    private fun getDeposit() {
        viewModel.getDeposit(args.idDeposit).observe(viewLifecycleOwner) { stateView ->
            when (stateView) {
                is StateView.Loading -> {}
                is StateView.Success -> {
                    stateView.data?.let { initUi(it) }
                }

                is StateView.Error -> {
                    Toast.makeText(requireContext(), "Error...", Toast.LENGTH_SHORT).show()
                    findNavController().popBackStack()
                }
            }
        }
    }

    private fun initUi(deposit: Deposit) {
        with(binding) {
            toolbarReceipt.txtTitle.text = getString(R.string.text_title_toolbar_receipt)
            toolbarReceipt.btnBack.ibBackWhite.setOnClickListener { findNavController().popBackStack() }
            textCodeReceipt.text = deposit.id
            textDateReceipt.text =
                GetMask.getFormatDate(deposit.date, DAY_MONTH_YEAR_HOUR_MINUTE)
            textValueReceipt.text = getString(
                R.string.text_format_value,
                GetMask.getFormatValue(deposit.amount)
            )
            btnNext.setOnClickListener { findNavController().popBackStack() }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}