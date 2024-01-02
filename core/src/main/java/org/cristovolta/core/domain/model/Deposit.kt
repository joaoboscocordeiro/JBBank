package org.cristovolta.core.domain.model

/**
 * Created by João Bosco on 13/11/2023.
 */
data class Deposit(
    var id: String = "",
    val date: Long = 0,
    val amount: Float = 0f
)
