package org.cristovolta.core.domain.model

/**
 * Created by João Bosco on 01/12/2023.
 */
data class Recharge(
    var id: String = "",
    val date: Long = 0L,
    val amount: Float = 0f,
    val number: String = ""
)