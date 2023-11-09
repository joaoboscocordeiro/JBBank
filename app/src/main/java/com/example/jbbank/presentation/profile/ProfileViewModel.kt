package com.example.jbbank.presentation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.core.domain.model.User
import com.example.core.usecase.ProfileUseCase
import com.example.jbbank.util.StateView
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import java.io.IOException
import javax.inject.Inject

/**
 * Created by João Bosco on 09/11/2023.
 */
@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val profileUseCase: ProfileUseCase
) : ViewModel() {

    fun save(user: User) = liveData(Dispatchers.IO) {
        try {
            emit(StateView.Loading())
            profileUseCase.invoke(user)
            emit(StateView.Success(null))
        } catch (ex: IOException) {
            emit(StateView.Error(ex.message))
        }
    }
}