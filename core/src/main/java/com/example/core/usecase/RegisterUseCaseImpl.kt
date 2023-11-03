package com.example.core.usecase

import com.example.core.data.repository.AuthFirebaseDataSource
import javax.inject.Inject

/**
 * Created by João Bosco on 03/11/2023.
 */
class RegisterUseCaseImpl @Inject constructor(
    private val authFirebaseDataSource: AuthFirebaseDataSource
) : RegisterUseCase {
    override suspend fun invoke(name: String, email: String, phone: String, password: String) {
        return authFirebaseDataSource.register(name, email, phone, password)
    }
}