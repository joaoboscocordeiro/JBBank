package com.example.core.usecase

import com.example.core.data.repository.AuthFirebaseDataSourceImpl

/**
 * Created by João Bosco on 03/11/2023.
 */
class RecoveryUseCase(
    private val authFirebaseDataSourceImpl: AuthFirebaseDataSourceImpl
) {
    suspend operator fun invoke(email: String) {
        return authFirebaseDataSourceImpl.recovery(email)
    }
}