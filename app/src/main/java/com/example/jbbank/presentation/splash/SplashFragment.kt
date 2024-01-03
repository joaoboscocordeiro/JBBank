package com.example.jbbank.presentation.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.jbbank.R
import com.example.jbbank.databinding.FragmentSplashBinding
import com.example.jbbank.framework.network.FirebaseHelper
import com.example.jbbank.util.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by João Bosco on 02/11/2023.
 */
@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding>(
    R.layout.fragment_splash,
    FragmentSplashBinding::bind
) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Handler(Looper.getMainLooper()).postDelayed(this::verifyAuth, DELAY_MILLIS)
    }

    private fun verifyAuth() {
        if (FirebaseHelper.isAuthenticated()) {
            findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
        } else {
            findNavController().navigate(R.id.action_splashFragment_to_autentication)
        }
    }

    companion object {
        private const val DELAY_MILLIS = 2000L
    }
}