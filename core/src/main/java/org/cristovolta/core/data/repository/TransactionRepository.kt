package org.cristovolta.core.data.repository

import org.cristovolta.core.domain.model.Transaction

/**
 * Created by João Bosco on 14/11/2023.
 */
interface TransactionRepository {
    suspend fun saveTransaction(transaction: Transaction)
    suspend fun getTransactions(): List<Transaction>
}