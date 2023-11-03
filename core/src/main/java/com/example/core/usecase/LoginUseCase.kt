package com.example.core.usecase

import com.example.core.data.repository.AuthFirebaseDataSourceImpl

/**
 * Created by João Bosco on 02/11/2023.
 */
class LoginUseCase(
    private val authFirebaseDataSourceImpl: AuthFirebaseDataSourceImpl
) {
    suspend operator fun invoke(email: String, password: String) {
        return authFirebaseDataSourceImpl.login(email, password)
    }
}