package com.example.jbbank.framework.di

import com.example.core.data.repository.AuthFirebaseDataSourceImpl
import com.example.core.usecase.LoginUseCase
import com.example.core.usecase.RecoveryUseCase
import com.example.core.usecase.RegisterUseCase
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
    fun bindLoginUseCase(useCase: AuthFirebaseDataSourceImpl): LoginUseCase

    @Binds
    fun bindRecoveryUseCase(useCase: AuthFirebaseDataSourceImpl): RecoveryUseCase

    @Binds
    fun bindRegisterUseCase(useCase: AuthFirebaseDataSourceImpl): RegisterUseCase
}