package org.cristovolta.core.usecase

import org.cristovolta.core.data.repository.AuthRepository
import org.cristovolta.core.domain.model.User
import javax.inject.Inject

/**
 * Created by João Bosco on 06/11/2023.
 */
class RegisterUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(name: String, email: String, phone: String, password: String): User {
        return authRepository.register(name, email, phone, password)
    }
}