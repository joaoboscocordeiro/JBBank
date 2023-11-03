package com.example.core.usecase

import com.example.core.data.repository.AuthFirebaseDataSourceImpl

/**
 * Created by João Bosco on 03/11/2023.
 */
class RegisterUseCase(
    private val authFirebaseDataSourceImpl: AuthFirebaseDataSourceImpl
) {
    suspend operator fun invoke(name: String, email: String, phone: String, password: String) {
        return authFirebaseDataSourceImpl.register(name, email, phone, password)
    }
}