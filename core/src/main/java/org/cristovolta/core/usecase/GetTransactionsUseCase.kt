package org.cristovolta.core.usecase

import org.cristovolta.core.data.repository.TransactionRepository
import org.cristovolta.core.domain.model.Transaction
import javax.inject.Inject

/**
 * Created by João Bosco on 14/11/2023.
 */
class GetTransactionsUseCase @Inject constructor(
    private val transactionRepository: TransactionRepository
) {
    suspend operator fun invoke(): List<Transaction> {
        return transactionRepository.getTransactions()
    }
}