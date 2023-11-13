package com.example.core.domain.model

import com.google.firebase.database.FirebaseDatabase

/**
 * Created by João Bosco on 13/11/2023.
 */
data class Deposit(
    var id: String = "",
    val date: Long = 0,
    val amount: Float = 0f
) {
    init {
        this.id = FirebaseDatabase.getInstance().reference.push().key ?: ""
    }
}
