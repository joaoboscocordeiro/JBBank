package com.example.core.data.repository.profile

import com.example.core.domain.model.User

/**
 * Created by João Bosco on 09/11/2023.
 */
interface ProfileDataSource {
    suspend fun saveProfile(user: User)
    suspend fun getProfile(): User
}