package org.cristovolta.core.data.repository

import org.cristovolta.core.domain.model.User

/**
 * Created by João Bosco on 09/11/2023.
 */
interface ProfileRepository {
    suspend fun saveProfile(user: User)
    suspend fun getProfile(): User
    suspend fun getProfileList(): List<User>
}