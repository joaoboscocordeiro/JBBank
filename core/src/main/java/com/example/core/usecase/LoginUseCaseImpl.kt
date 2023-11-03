package com.example.core.usecase

import com.example.core.data.repository.AuthFirebaseDataSource
import javax.inject.Inject

/**
 * Created by João Bosco on 03/11/2023.
 */
class LoginUseCaseImpl @Inject constructor(
    private val authFirebaseDataSource: AuthFirebaseDataSource
) : LoginUseCase {
    override suspend fun invoke(email: String, password: String) {
        return authFirebaseDataSource.login(email, password)
    }
}