package com.example.jbbank.framework.di

import com.example.core.data.repository.auth.AuthFirebaseDataSource
import com.example.core.data.repository.auth.AuthFirebaseDataSourceImpl
import com.example.core.data.repository.deposit.DepositDataSource
import com.example.core.data.repository.deposit.DepositDataSourceImpl
import com.example.core.data.repository.recharge.RechargeDataSource
import com.example.core.data.repository.recharge.RechargeDataSourceImpl
import com.example.core.data.repository.transaction.TransactionDataSource
import com.example.core.data.repository.transaction.TransactionDataSourceImpl
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
    abstract fun bindAuthDataSource(dataSource: AuthFirebaseDataSourceImpl): AuthFirebaseDataSource

    @Binds
    abstract fun bindDepositDataSource(dataSource: DepositDataSourceImpl): DepositDataSource

    @Binds
    abstract fun bindTransactionDataSource(dataSource: TransactionDataSourceImpl): TransactionDataSource

    @Binds
    abstract fun bindRechargeDataSource(dataSource: RechargeDataSourceImpl): RechargeDataSource
}