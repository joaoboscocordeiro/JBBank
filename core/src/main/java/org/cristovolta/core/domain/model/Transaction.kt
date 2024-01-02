package org.cristovolta.core.domain.model

import org.cristovolta.core.domain.enum.TransactionOperation
import org.cristovolta.core.domain.enum.TransactionType

/**
 * Created by João Bosco on 10/11/2023.
 */
data class Transaction(
    var id: String = "",
    val operation: TransactionOperation? = null,
    val date: Long = 0,
    val amount: Float = 0f,
    val type: TransactionType? = null
)
