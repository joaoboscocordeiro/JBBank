package com.example.jbbank.presentation.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.jbbank.R
import com.example.jbbank.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by João Bosco on 27/11/2023.
 */
@AndroidEntryPoint
class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private var _binding: FragmentProfileBinding? = null
    private val binding: FragmentProfileBinding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentProfileBinding.bind(view)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}