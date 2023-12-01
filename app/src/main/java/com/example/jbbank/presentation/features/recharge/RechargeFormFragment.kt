package com.example.jbbank.presentation.features.recharge

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.jbbank.R
import com.example.jbbank.databinding.FragmentRechargeFormBinding
import com.example.jbbank.util.BaseFragment
import com.example.jbbank.util.GetMask.PHONE_QUANTITY
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
                    Snackbar.make(requireView(), "tudo ok.", Snackbar.LENGTH_SHORT).show()

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
}