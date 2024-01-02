package org.cristovolta.core.usecase

import org.cristovolta.core.data.repository.ProfileRepository
import org.cristovolta.core.domain.model.User
import javax.inject.Inject

/**
 * Created by João Bosco on 07/12/2023.
 */
class GetProfileListUseCase @Inject constructor(
    private val profileRepository: ProfileRepository
) {
    suspend operator fun invoke(): List<User> {
        return profileRepository.getProfileList()
    }
}