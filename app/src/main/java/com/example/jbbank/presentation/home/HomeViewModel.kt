package com.example.jbbank.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.jbbank.util.StateView
import com.google.firebase.database.DatabaseException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import org.cristovolta.core.usecase.GetTransactionsUseCase
import org.cristovolta.core.usecase.GetWalletUseCase
import javax.inject.Inject

/**
 * Created by João Bosco on 10/11/2023.
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getWalletUseCase: GetWalletUseCase,
    private val getTransactionsUseCase: GetTransactionsUseCase
) : ViewModel() {

    fun getWallet() = liveData(Dispatchers.IO) {
        try {
            emit(StateView.Loading())
            val wallet = getWalletUseCase.invoke()
            emit(StateView.Success(wallet))
        } catch (ex: DatabaseException) {
            emit(StateView.Error(ex.message))
        }
    }

    @Suppress("TooGenericExceptionCaught")
    fun getTransactions() = liveData(Dispatchers.IO) {
        try {
            emit(StateView.Loading())
            val transactions = getTransactionsUseCase.invoke()
            emit(StateView.Success(transactions))
        } catch (ex: Exception) {
            emit(StateView.Error(ex.message))
        }
    }
}