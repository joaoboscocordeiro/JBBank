package com.example.jbbank.presentation.features.recharge

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.jbbank.util.StateView
import com.google.firebase.database.DatabaseException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import org.cristovolta.core.domain.model.Recharge
import org.cristovolta.core.domain.model.Transaction
import org.cristovolta.core.usecase.SaveRechargeUseCase
import org.cristovolta.core.usecase.SaveTransactionUseCase
import javax.inject.Inject

/**
 * Created by João Bosco on 01/12/2023.
 */
@HiltViewModel
class RechargeViewModel @Inject constructor(
    private val saveRechargeUseCase: SaveRechargeUseCase,
    private val saveTransactionUseCase: SaveTransactionUseCase
) : ViewModel() {

    fun saveRecharge(recharge: Recharge) = liveData(Dispatchers.IO) {
        try {
            emit(StateView.Loading())
            saveRechargeUseCase.invoke(recharge)
            emit(StateView.Success(recharge))
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