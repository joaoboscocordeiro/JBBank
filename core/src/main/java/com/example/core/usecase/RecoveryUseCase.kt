package com.example.core.usecase

/**
 * Created by João Bosco on 03/11/2023.
 */
interface RecoveryUseCase {
    suspend operator fun invoke(email: String)
}