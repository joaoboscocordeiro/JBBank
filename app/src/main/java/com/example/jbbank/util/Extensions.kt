package com.example.jbbank.util

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.example.jbbank.R
import com.example.jbbank.databinding.BottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialog

/**
 * Created by João Bosco on 07/11/2023.
 */
fun Fragment.initToolbar(toolbar: Toolbar, homeAsUpEnabled: Boolean = true) {
    (activity as AppCompatActivity).setSupportActionBar(toolbar)
    (activity as AppCompatActivity).title = ""
    (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(homeAsUpEnabled)
    (activity as AppCompatActivity).supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back)
    toolbar.setNavigationOnClickListener { activity?.onBackPressedDispatcher }
}

fun Fragment.showBottomSheet(
    titleDialog: Int? = null,
    titleButton: Int? = null,
    message: String,
    onClick: () -> Unit = {}
) {
    val bottomSheetDialog = BottomSheetDialog(requireContext(), R.style.Bottom_Sheet_Dialog)
    val bottomSheetBinding: BottomSheetBinding =
        BottomSheetBinding.inflate(layoutInflater, null, false)

    bottomSheetBinding.sheerTxtTitle.text =
        getString(titleDialog ?: R.string.text_title_bottom_sheet)
    bottomSheetBinding.sheerTxtMassage.text = message
    bottomSheetBinding.btnSheet.text =
        getString(titleButton ?: R.string.text_button_bottom_sheet)
    bottomSheetBinding.btnSheet.setOnClickListener {
        bottomSheetDialog.dismiss()
        onClick()
    }

    bottomSheetDialog.setContentView(bottomSheetBinding.root)
    bottomSheetDialog.show()
}