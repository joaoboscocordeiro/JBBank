package com.example.core.usecase

import com.example.core.data.repository.transaction.TransactionDataSourceImpl
import com.example.core.domain.model.Transaction
import javax.inject.Inject

/**
 * Created by João Bosco on 14/11/2023.
 */
class SaveTransactionUseCase @Inject constructor(
    private val transactionDataSourceImpl: TransactionDataSourceImpl
) {
    suspend operator fun invoke(transaction: Transaction) {
        return transactionDataSourceImpl.saveTransaction(transaction)
    }
}