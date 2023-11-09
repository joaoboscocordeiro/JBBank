package com.example.jbbank.presentation.wallet

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.core.domain.model.Wallet
import com.example.core.usecase.WalletUseCase
import com.example.jbbank.util.StateView
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import java.io.IOException
import javax.inject.Inject

/**
 * Created by João Bosco on 09/11/2023.
 */
@HiltViewModel
class WalletViewModel @Inject constructor(
    private val walletUseCase: WalletUseCase
) : ViewModel() {

    fun initWallet(wallet: Wallet) = liveData(Dispatchers.IO) {
        try {
            emit(StateView.Loading())
            walletUseCase.invoke(wallet)
            emit(StateView.Success(null))
        } catch (ex: IOException) {
            emit(StateView.Error(ex.message))
        }
    }
}