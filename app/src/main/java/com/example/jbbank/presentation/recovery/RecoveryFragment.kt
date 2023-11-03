package com.example.jbbank.presentation.recovery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.jbbank.databinding.FragmentRecoveryBinding

/**
 * Created by João Bosco on 02/11/2023.
 */
class RecoveryFragment : Fragment() {

    private var _binding: FragmentRecoveryBinding? = null
    private val binding: FragmentRecoveryBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentRecoveryBinding.inflate(inflater, container, false)
        .apply { _binding = this }.root

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}