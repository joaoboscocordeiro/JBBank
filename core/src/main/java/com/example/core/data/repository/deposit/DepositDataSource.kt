package com.example.core.data.repository.deposit

import com.example.core.domain.model.Deposit

/**
 * Created by João Bosco on 13/11/2023.
 */
interface DepositDataSource {
    suspend fun saveDeposit(deposit: Deposit): Deposit
}