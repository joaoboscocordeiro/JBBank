package com.example.jbbank.presentation.recovery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import org.cristovolta.core.usecase.RecoveryUseCase
import com.example.jbbank.util.StateView
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

/**
 * Created by João Bosco on 03/11/2023.
 */
@HiltViewModel
class RecoveryViewModel @Inject constructor(
    private val recoveryUseCase: RecoveryUseCase
) : ViewModel() {

    @Suppress("TooGenericExceptionCaught")
    fun recovery(email: String) = liveData(Dispatchers.IO) {
        try {
            emit(StateView.Loading())
            recoveryUseCase.invoke(email)
            emit(StateView.Success(null))
        } catch (ex: Exception) {
            emit(StateView.Error(ex.message))
        }
    }
}