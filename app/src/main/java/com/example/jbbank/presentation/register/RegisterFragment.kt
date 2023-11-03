package com.example.jbbank.presentation.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.jbbank.databinding.FragmentRegisterBinding

/**
 * Created by João Bosco on 02/11/2023.
 */
class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding: FragmentRegisterBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentRegisterBinding.inflate(inflater, container, false)
        .apply { _binding = this }.root

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}