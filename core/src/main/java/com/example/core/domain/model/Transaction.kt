package com.example.core.domain.model

import com.google.firebase.database.FirebaseDatabase

/**
 * Created by João Bosco on 10/11/2023.
 */
data class Transaction(
    var id: String = "",
    val description: String = "",
    val date: Long = 0,
    val value: Float = 0f
) {
    init {
        this.id = FirebaseDatabase.getInstance().reference.push().key ?: ""
    }
}
