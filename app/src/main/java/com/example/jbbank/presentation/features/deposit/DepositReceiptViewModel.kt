package com.example.jbbank.presentation.features.deposit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.core.usecase.GetDepositUseCase
import com.example.jbbank.util.StateView
import com.google.firebase.database.DatabaseException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

/**
 * Created by João Bosco on 22/11/2023.
 */
@HiltViewModel
class DepositReceiptViewModel @Inject constructor(
    private val getDepositUseCase: GetDepositUseCase
) : ViewModel() {

    fun getDeposit(id: String) = liveData(Dispatchers.IO) {
        try {
            emit(StateView.Loading())
            val deposit = getDepositUseCase.invoke(id)
            emit(StateView.Success(deposit))
        } catch (ex: DatabaseException) {
            emit(StateView.Error(ex.message))
        }
    }
}