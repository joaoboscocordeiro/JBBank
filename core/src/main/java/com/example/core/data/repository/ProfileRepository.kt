package com.example.core.data.repository

import com.example.core.domain.model.User

/**
 * Created by João Bosco on 09/11/2023.
 */
interface ProfileRepository {
    suspend fun saveProfile(user: User)
}