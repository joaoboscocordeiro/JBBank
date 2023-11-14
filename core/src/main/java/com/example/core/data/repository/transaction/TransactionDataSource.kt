package com.example.core.data.repository.transaction

import com.example.core.domain.model.Transaction

/**
 * Created by João Bosco on 14/11/2023.
 */
interface TransactionDataSource {
    suspend fun saveTransaction(transaction: Transaction)
    suspend fun getTransactions(): List<Transaction>
}