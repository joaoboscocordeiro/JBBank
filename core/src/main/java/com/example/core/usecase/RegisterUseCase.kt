package com.example.core.usecase

import com.example.core.data.repository.AuthFirebaseDataSourceImpl
import com.example.core.domain.model.User
import javax.inject.Inject

/**
 * Created by João Bosco on 06/11/2023.
 */
class RegisterUseCase @Inject constructor(
    private val authFirebaseDataSourceImpl: AuthFirebaseDataSourceImpl
) {
    suspend operator fun invoke(name: String, email: String, phone: String, password: String): User {
        return authFirebaseDataSourceImpl.register(name, email, phone, password)
    }
}