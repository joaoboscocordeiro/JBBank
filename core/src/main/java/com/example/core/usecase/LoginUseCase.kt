package com.example.core.usecase

/**
 * Created by João Bosco on 02/11/2023.
 */
interface LoginUseCase {
    suspend operator fun invoke(email: String, password: String)
}