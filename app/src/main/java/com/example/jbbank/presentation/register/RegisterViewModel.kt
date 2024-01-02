package com.example.jbbank.presentation.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import org.cristovolta.core.usecase.RegisterUseCase
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
    fun register(name: String, email: String, phone: String, password: String) = liveData(Dispatchers.IO) {
        try {
            emit(StateView.Loading())
            val user = registerUseCase.invoke(name, email, phone, password)
            emit(StateView.Success(user))
        } catch (ex: Exception) {
            emit(StateView.Error(ex.message))
        }
    }
}