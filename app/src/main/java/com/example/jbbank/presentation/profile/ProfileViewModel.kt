package com.example.jbbank.presentation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.core.domain.model.User
import com.example.core.usecase.GetProfileUseCase
import com.example.core.usecase.ProfileUseCase
import com.example.jbbank.util.StateView
import com.google.firebase.database.DatabaseException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

/**
 * Created by João Bosco on 09/11/2023.
 */
@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val profileUseCase: ProfileUseCase,
    private val getProfileUseCase: GetProfileUseCase
) : ViewModel() {

    @Suppress("TooGenericExceptionCaught")
    fun saveProfile(user: User) = liveData(Dispatchers.IO) {
        try {
            emit(StateView.Loading())
            profileUseCase.invoke(user)
            emit(StateView.Success(null))
        } catch (ex: Exception) {
            emit(StateView.Error(ex.message))
        }
    }

    fun getProfile() = liveData(Dispatchers.IO) {
        try {
            emit(StateView.Loading())
            val user = getProfileUseCase.invoke()
            emit(StateView.Success(user))
        } catch (ex: DatabaseException) {
            emit(StateView.Error(ex.message))
        }
    }
}