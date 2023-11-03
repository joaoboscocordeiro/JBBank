package com.example.jbbank.framework.di

import com.example.core.usecase.LoginUseCase
import com.example.core.usecase.LoginUseCaseImpl
import com.example.core.usecase.RecoveryUseCase
import com.example.core.usecase.RecoveryUseCaseImpl
import com.example.core.usecase.RegisterUseCase
import com.example.core.usecase.RegisterUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

/**
 * Created by João Bosco on 03/11/2023.
 */
@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {

    @Binds
    fun bindLoginUseCase(useCase: LoginUseCaseImpl): LoginUseCase

    @Binds
    fun bindRecoveryUseCase(useCase: RecoveryUseCaseImpl): RecoveryUseCase

    @Binds
    fun bindRegisterUseCase(useCase: RegisterUseCaseImpl): RegisterUseCase
}