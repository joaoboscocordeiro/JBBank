package org.cristovolta.core.domain.enum

/**
 * Created by João Bosco on 14/11/2023.
 */
enum class TransactionOperation {

    DEPOSIT,
    RECHARGE;

    companion object {
        fun getOperation(operation: TransactionOperation): String {
            return when (operation) {
                DEPOSIT -> {
                    "DEPÓSITO"
                }

                RECHARGE -> {
                    "RECARGA"
                }
            }
        }
    }
}