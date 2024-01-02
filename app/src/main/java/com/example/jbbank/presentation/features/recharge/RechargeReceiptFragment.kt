package com.example.jbbank.presentation.features.recharge

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.jbbank.R
import com.example.jbbank.databinding.FragmentRechargeReceiptBinding
import com.example.jbbank.util.BaseFragment
import com.example.jbbank.util.GetMask
import com.example.jbbank.util.GetMask.DAY_MONTH_YEAR_HOUR_MINUTE
import com.example.jbbank.util.StateView
import dagger.hilt.android.AndroidEntryPoint
import org.cristovolta.core.domain.model.Recharge

/**
 * Created by João Bosco on 30/11/2023.
 */
@AndroidEntryPoint
class RechargeReceiptFragment : BaseFragment<FragmentRechargeReceiptBinding>(
    R.layout.fragment_recharge_receipt,
    FragmentRechargeReceiptBinding::bind
) {

    private val args: RechargeReceiptFragmentArgs by navArgs()

    private val rechargeReceiptViewModel: RechargeReceiptViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUi()
        getRecharge()
    }

    private fun initUi() {
        binding?.toolbarRecharge?.txtTitle?.text = getString(R.string.text_title_toolbar_recharge)
        binding?.toolbarRecharge?.btnBack?.ibBackWhite?.setOnClickListener {
            findNavController().popBackStack()
        }
        binding?.btnNext?.setOnClickListener { validateRecharge() }
    }

    private fun validateRecharge() {
       findNavController().popBackStack()
    }

    private fun getRecharge() {
        rechargeReceiptViewModel.getRecharge(args.idRecharge)
            .observe(viewLifecycleOwner) { stateView ->
                when (stateView) {
                    is StateView.Loading -> {}
                    is StateView.Success -> {
                        stateView.data?.let { configData(it) }
                    }

                    is StateView.Error -> {
                        Toast.makeText(requireContext(), "ERROR", Toast.LENGTH_SHORT).show()
                        findNavController().popBackStack()
                    }
                }
            }
    }

    private fun configData(recharge: Recharge) {
        binding?.textCodeRecharge?.text = recharge.id
        binding?.textDateRecharge?.text =
            GetMask.getFormatDate(recharge.date, DAY_MONTH_YEAR_HOUR_MINUTE)
        binding?.textValueRecharge?.text =
            getString(R.string.text_format_value, GetMask.getFormatValue(recharge.amount))
        binding?.textPhoneRecharge?.text = recharge.number
    }
}