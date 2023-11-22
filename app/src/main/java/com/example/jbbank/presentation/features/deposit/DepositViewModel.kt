package com.example.jbbank.presentation.features.deposit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.core.domain.model.Deposit
import com.example.core.domain.model.Transaction
import com.example.core.usecase.DepositUseCase
import com.example.core.usecase.SaveTransactionUseCase
import com.example.jbbank.util.StateView
import com.google.firebase.database.DatabaseException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

/**
 * Created by João Bosco on 13/11/2023.
 */
@HiltViewModel
class DepositViewModel @Inject constructor(
    private val depositUseCase: DepositUseCase,
    private val saveTransactionUseCase: SaveTransactionUseCase
) : ViewModel() {

    fun saveDeposit(deposit: Deposit) = liveData(Dispatchers.IO) {
        try {
            emit(StateView.Loading())
            depositUseCase.invoke(deposit)
            emit(StateView.Success(deposit))
        } catch (ex: DatabaseException) {
            emit(StateView.Error(ex.message))
        }
    }

    fun saveTransaction(transaction: Transaction) = liveData(Dispatchers.IO) {
        try {
            emit(StateView.Loading())
            saveTransactionUseCase.invoke(transaction)
            emit(StateView.Success(Unit))
        } catch (ex: DatabaseException) {
            emit(StateView.Error(ex.message))
        }
    }
}