package org.cristovolta.core.data.repository

import org.cristovolta.core.domain.model.Deposit

/**
 * Created by João Bosco on 13/11/2023.
 */
interface DepositRepository {
    suspend fun saveDeposit(deposit: Deposit): Deposit
    suspend fun getDeposit(id: String): Deposit
}