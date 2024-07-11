package com.example.jbbank.presentation.features.transfer

import android.os.Bundle
import android.view.View
import com.example.jbbank.R
import com.example.jbbank.databinding.FragmentTransferReceiptBinding
import com.example.jbbank.util.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by João Bosco on 10/07/2024.
 */
@AndroidEntryPoint
class TransferReceiptFragment : BaseFragment<FragmentTransferReceiptBinding>(
    R.layout.fragment_transfer_receipt,
    FragmentTransferReceiptBinding::bind
) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}