package com.example.jbbank.presentation.features.transfer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.core.usecase.GetProfileListUseCase
import com.example.jbbank.util.StateView
import com.google.firebase.database.DatabaseException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

/**
 * Created by João Bosco on 07/12/2023.
 */
@HiltViewModel
class TransferUserListViewModel @Inject constructor(
    private val getProfileListUseCase: GetProfileListUseCase
) : ViewModel() {

    fun getProfileList() = liveData(Dispatchers.IO) {
        try {
            emit(StateView.Loading())
            val userList = getProfileListUseCase.invoke()
            emit(StateView.Success(userList))
        } catch (ex: DatabaseException) {
            emit(StateView.Error(ex.message))
        }
    }
}