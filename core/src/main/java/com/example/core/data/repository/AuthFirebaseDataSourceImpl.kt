package com.example.core.data.repository

/**
 * Created by João Bosco on 02/11/2023.
 */
class AuthFirebaseDataSourceImpl(

): AuthFirebaseDataSource {
    override suspend fun login(email: String, password: String) {
        TODO("Not yet implemented")
    }

    override suspend fun register(name: String, email: String, phone: String, password: String) {
        TODO("Not yet implemented")
    }

    override suspend fun recovery(email: String) {
        TODO("Not yet implemented")
    }
}