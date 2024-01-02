package org.cristovolta.core.data.repository

import org.cristovolta.core.domain.model.User

/**
 * Created by João Bosco on 03/11/2023.
 */
interface AuthRepository {
    suspend fun login(email: String, password: String)
    suspend fun register(name: String, email: String, phone: String, password: String): User
    suspend fun recovery(email: String)
}