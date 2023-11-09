package com.example.core.usecase

import com.example.core.data.repository.auth.AuthFirebaseDataSourceImpl
import javax.inject.Inject

/**
 * Created by João Bosco on 06/11/2023.
 */
class LoginUseCase @Inject constructor(
    private val authFirebaseDataSourceImpl: AuthFirebaseDataSourceImpl
) {
    suspend operator fun invoke(email: String, password: String) {
        return authFirebaseDataSourceImpl.login(email, password)
    }
}