package com.example.core.usecase

import com.example.core.data.repository.ProfileRepositoryImpl
import com.example.core.domain.model.User
import javax.inject.Inject

/**
 * Created by João Bosco on 09/11/2023.
 */
class ProfileUseCase @Inject constructor(
    private val profileRepositoryImpl: ProfileRepositoryImpl
) {
    suspend operator fun invoke(user: User) {
        return profileRepositoryImpl.saveProfile(user)
    }
}