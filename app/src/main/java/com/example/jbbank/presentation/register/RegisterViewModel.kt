package com.example.jbbank.presentation.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.core.domain.model.User
import com.example.core.usecase.RegisterUseCase
import com.example.jbbank.util.StateView
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

/**
 * Created by João Bosco on 03/11/2023.
 */
@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase
) : ViewModel() {

    @Suppress("TooGenericExceptionCaught")
    fun register(user: User) = liveData(Dispatchers.IO) {
        try {
            emit(StateView.Loading())
            registerUseCase.invoke(user)
            emit(StateView.Success(user))
        } catch (ex: Exception) {
            emit(StateView.Error(ex.message))
        }
    }
}