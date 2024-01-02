package com.example.jbbank.framework.di

import com.example.jbbank.framework.remote.FirebaseAuthDataSourceImpl
import com.example.jbbank.framework.remote.FirebaseDepositDataSourceImpl
import com.example.jbbank.framework.remote.FirebaseProfileDataSourceImpl
import com.example.jbbank.framework.remote.FirebaseRechargeDataSourceImpl
import com.example.jbbank.framework.remote.FirebaseTransactionDataSourceImpl
import com.example.jbbank.framework.remote.FirebaseWalletDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import org.cristovolta.core.data.repository.AuthRepository
import org.cristovolta.core.data.repository.DepositRepository
import org.cristovolta.core.data.repository.ProfileRepository
import org.cristovolta.core.data.repository.RechargeRepository
import org.cristovolta.core.data.repository.TransactionRepository
import org.cristovolta.core.data.repository.WalletRepository

/**
 * Created by João Bosco on 03/11/2023.
 */
@Module
@InstallIn(ViewModelComponent::class)
abstract class UseCaseModule {

    @Binds
    abstract fun bindAuthDataSource(dataSource: FirebaseAuthDataSourceImpl): AuthRepository

    @Binds
    abstract fun bindDepositDataSource(dataSource: FirebaseDepositDataSourceImpl): DepositRepository

    @Binds
    abstract fun bindProfileDataSource(dataSource: FirebaseProfileDataSourceImpl): ProfileRepository

    @Binds
    abstract fun bindTransactionDataSource(dataSource: FirebaseTransactionDataSourceImpl): TransactionRepository

    @Binds
    abstract fun bindRechargeDataSource(dataSource: FirebaseRechargeDataSourceImpl): RechargeRepository

    @Binds
    abstract fun bindWalletDataSource(dataSource: FirebaseWalletDataSourceImpl): WalletRepository
}