package com.example.jbbank.presentation.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.jbbank.databinding.FragmentLoginBinding

/**
 * Created by João Bosco on 02/11/2023.
 */
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding: FragmentLoginBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentLoginBinding.inflate(inflater, container, false)
        .apply { _binding = this }.root

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}