package org.cristovolta.core.usecase

import org.cristovolta.core.data.repository.ProfileRepository
import org.cristovolta.core.domain.model.User
import javax.inject.Inject

/**
 * Created by João Bosco on 09/11/2023.
 */
class SaveProfileUseCase @Inject constructor(
    private val profileRepository: ProfileRepository
) {
    suspend operator fun invoke(user: User) {
        return profileRepository.saveProfile(user)
    }
}