package com.example.jbbank.presentation.features.recharge

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.core.usecase.GetRechargeUseCase
import com.example.jbbank.util.StateView
import com.google.firebase.database.DatabaseException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

/**
 * Created by João Bosco on 06/12/2023.
 */
@HiltViewModel
class RechargeReceiptViewModel @Inject constructor(
    private val getRechargeUseCase: GetRechargeUseCase
) : ViewModel() {

    fun getRecharge(id: String) = liveData(Dispatchers.IO) {
        try {
            emit(StateView.Loading())
            val recharge = getRechargeUseCase.invoke(id)
            emit(StateView.Success(recharge))
        } catch (ex: DatabaseException) {
            emit(StateView.Error(ex.message))
        }
    }
}