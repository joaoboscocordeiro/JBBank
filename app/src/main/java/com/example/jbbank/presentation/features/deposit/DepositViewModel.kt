package com.example.jbbank.presentation.features.deposit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.jbbank.util.StateView
import com.google.firebase.database.DatabaseException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import org.cristovolta.core.domain.model.Deposit
import org.cristovolta.core.domain.model.Transaction
import org.cristovolta.core.usecase.SaveDepositUseCase
import org.cristovolta.core.usecase.SaveTransactionUseCase
import javax.inject.Inject

/**
 * Created by João Bosco on 13/11/2023.
 */
@HiltViewModel
class DepositViewModel @Inject constructor(
    private val saveDepositUseCase: SaveDepositUseCase,
    private val saveTransactionUseCase: SaveTransactionUseCase
) : ViewModel() {

    fun saveDeposit(deposit: Deposit) = liveData(Dispatchers.IO) {
        try {
            emit(StateView.Loading())
            saveDepositUseCase.invoke(deposit)
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