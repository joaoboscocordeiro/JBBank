package com.example.jbbank.presentation.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.jbbank.databinding.FragmentSplashBinding

/**
 * Created by João Bosco on 02/11/2023.
 */
class SplashFragment : Fragment() {

    private var _binding: FragmentSplashBinding? = null
    private val binding: FragmentSplashBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentSplashBinding.inflate(inflater, container, false)
        .apply { _binding = this }.root

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}