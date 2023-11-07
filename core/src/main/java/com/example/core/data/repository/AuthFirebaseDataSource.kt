package com.example.core.data.repository

import com.example.core.domain.model.User

/**
 * Created by João Bosco on 03/11/2023.
 */
interface AuthFirebaseDataSource {
    suspend fun login(email: String, password: String)
    suspend fun register(user: User): User
    suspend fun recovery(email: String)
}