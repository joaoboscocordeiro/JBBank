package com.example.jbbank.presentation.wallet

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import org.cristovolta.core.domain.model.Wallet
import org.cristovolta.core.usecase.InitWalletUseCase
import com.example.jbbank.util.StateView
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

/**
 * Created by João Bosco on 09/11/2023.
 */
@HiltViewModel
class WalletViewModel @Inject constructor(
    private val initWalletUseCase: InitWalletUseCase
) : ViewModel() {

    @Suppress("TooGenericExceptionCaught")
    fun initWallet(wallet: Wallet) = liveData(Dispatchers.IO) {
        try {
            emit(StateView.Loading())
            initWalletUseCase.invoke(wallet)
            emit(StateView.Success(null))
        } catch (ex: Exception) {
            emit(StateView.Error(ex.message))
        }
    }
}