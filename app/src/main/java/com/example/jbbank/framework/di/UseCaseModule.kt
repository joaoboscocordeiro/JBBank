package com.example.jbbank.framework.di

import com.example.core.data.repository.AuthFirebaseDataSource
import com.example.core.data.repository.AuthFirebaseDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

/**
 * Created by João Bosco on 03/11/2023.
 */
@Module
@InstallIn(ViewModelComponent::class)
abstract class UseCaseModule {

    @Binds
    abstract fun bindLoginUseCase(useCase: AuthFirebaseDataSourceImpl): AuthFirebaseDataSource

//    @Binds
//    abstract fun bindRecoveryUseCase(useCase: AuthFirebaseDataSourceImpl): RecoveryUseCase
//
//    @Binds
//    abstract fun bindRegisterUseCase(useCase: AuthFirebaseDataSourceImpl): RegisterUseCase
}