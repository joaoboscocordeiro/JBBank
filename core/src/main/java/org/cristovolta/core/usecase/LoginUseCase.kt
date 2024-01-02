package org.cristovolta.core.usecase

import org.cristovolta.core.data.repository.AuthRepository
import javax.inject.Inject

/**
 * Created by João Bosco on 06/11/2023.
 */
class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(email: String, password: String) {
        return authRepository.login(email, password)
    }
}