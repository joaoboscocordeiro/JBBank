package com.example.core.usecase

import com.example.core.data.repository.AuthFirebaseDataSource
import javax.inject.Inject

/**
 * Created by João Bosco on 03/11/2023.
 */
class RecoveryUseCaseImpl @Inject constructor(
    private val authFirebaseDataSource: AuthFirebaseDataSource
) : RecoveryUseCase {
    override suspend fun invoke(email: String) {
        return authFirebaseDataSource.recovery(email)
    }
}