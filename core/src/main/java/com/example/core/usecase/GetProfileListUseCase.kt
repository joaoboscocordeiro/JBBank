package com.example.core.usecase

import com.example.core.data.repository.profile.ProfileDataSourceImpl
import com.example.core.domain.model.User
import javax.inject.Inject

/**
 * Created by João Bosco on 07/12/2023.
 */
class GetProfileListUseCase @Inject constructor(
    private val profileDataSourceImpl: ProfileDataSourceImpl
) {
    suspend operator fun invoke(): List<User> {
        return profileDataSourceImpl.getProfileList()
    }
}