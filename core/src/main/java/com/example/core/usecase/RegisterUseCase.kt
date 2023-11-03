package com.example.core.usecase

/**
 * Created by João Bosco on 03/11/2023.
 */
interface RegisterUseCase {
    suspend operator fun invoke(name: String, email: String, phone: String, password: String)
}