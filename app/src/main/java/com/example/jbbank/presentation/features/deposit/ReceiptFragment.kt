package com.example.jbbank.presentation.features.deposit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.jbbank.R
import com.example.jbbank.databinding.FragmentReceiptBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by João Bosco on 12/11/2023.
 */
@AndroidEntryPoint
class ReceiptFragment : Fragment() {

    private var _binding: FragmentReceiptBinding? = null
    private val binding: FragmentReceiptBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentReceiptBinding.inflate(inflater, container, false)
        .apply { _binding = this }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUi()
    }

    private fun initUi() {
        with(binding) {
            toolbarReceipt.txtTitle.text = getString(R.string.text_title_toolbar_receipt)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}