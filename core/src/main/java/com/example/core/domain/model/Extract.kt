package com.example.core.domain.model

import com.google.firebase.database.FirebaseDatabase

/**
 * Created by João Bosco on 13/11/2023.
 */
data class Extract(
    var id: String = "",
    val operation: String = "",
    val date: Long = 0,
    val amount: Float = 0f,
    val type: String = ""
) {
    init {
        this.id = FirebaseDatabase.getInstance().reference.push().key ?: ""
    }
}
