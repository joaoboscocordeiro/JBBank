package org.cristovolta.core.domain.enum

/**
 * Created by João Bosco on 14/11/2023.
 */
enum class TransactionType {
    CASH_IN,
    CASH_OUT;

    companion object {
        fun getType(operation: TransactionOperation): Char {
            return when (operation) {
                TransactionOperation.DEPOSIT -> {
                    'D'
                }

                TransactionOperation.RECHARGE -> {
                    'R'
                }
            }
        }
    }
}