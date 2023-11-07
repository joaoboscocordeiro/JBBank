package com.example.core.usecase

import com.example.core.data.repository.AuthFirebaseDataSourceImpl
import javax.inject.Inject

/**
 * Created by João Bosco on 06/11/2023.
 */
class RecoveryUseCase @Inject constructor(
    private val authFirebaseDataSourceImpl: AuthFirebaseDataSourceImpl
) {
    suspend operator fun invoke(email: String) {
        return authFirebaseDataSourceImpl.recovery(email)
    }
}