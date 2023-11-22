package com.example.jbbank.presentation.features.extract

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.core.usecase.GetTransactionsUseCase
import com.example.jbbank.util.StateView
import com.google.firebase.database.DatabaseException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

/**
 * Created by João Bosco on 22/11/2023.
 */
@HiltViewModel
class ExtractViewModel @Inject constructor(
    private val getTransactionsUseCase: GetTransactionsUseCase
) : ViewModel() {

    fun getExtract() = liveData(Dispatchers.IO) {
        try {
            emit(StateView.Loading())
            val extract = getTransactionsUseCase.invoke()
            emit(StateView.Success(extract))
        } catch (ex: DatabaseException) {
            emit(StateView.Error(ex.message))
        }
    }
}