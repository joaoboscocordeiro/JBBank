package com.example.jbbank.presentation.features.recharge

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.jbbank.R
import com.example.jbbank.databinding.FragmentRechargeReceiptBinding
import com.example.jbbank.util.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by João Bosco on 30/11/2023.
 */
@AndroidEntryPoint
class RechargeReceiptFragment : BaseFragment<FragmentRechargeReceiptBinding>(
    R.layout.fragment_recharge_receipt,
    FragmentRechargeReceiptBinding::bind
) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUi()
    }

    private fun initUi() {
        binding?.toolbarRecharge?.txtTitle?.text = getString(R.string.text_title_toolbar_recharge)
        binding?.toolbarRecharge?.btnBack?.ibBackWhite?.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}