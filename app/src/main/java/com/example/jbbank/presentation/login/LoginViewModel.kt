package com.example.jbbank.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.core.usecase.LoginUseCase
import com.example.jbbank.util.StateView
import com.google.firebase.database.DatabaseException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

/**
 * Created by João Bosco on 03/11/2023.
 */
@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    fun login(email: String, password: String) = liveData(Dispatchers.IO) {
        try {
            emit(StateView.Loading())
            loginUseCase.invoke(email, password)
            emit(StateView.Success(null))
        } catch (ex: DatabaseException) {
            emit(StateView.Error(ex.message))
        }
    }
}