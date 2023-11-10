package com.example.jbbank.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.core.usecase.GetWalletUseCase
import com.example.jbbank.util.StateView
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

/**
 * Created by João Bosco on 10/11/2023.
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getWalletUseCase: GetWalletUseCase
) : ViewModel() {

    @Suppress("TooGenericExceptionCaught")
    fun getWallet() = liveData(Dispatchers.IO) {
        try {
            emit(StateView.Loading())
            val wallet = getWalletUseCase.invoke()
            emit(StateView.Success(wallet))
        } catch (ex: Exception) {
            emit(StateView.Error(ex.message))
        }
    }
}