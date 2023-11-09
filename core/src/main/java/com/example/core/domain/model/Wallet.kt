package com.example.core.domain.model

import com.google.firebase.database.FirebaseDatabase

/**
 * Created by João Bosco on 09/11/2023.
 */
data class Wallet(
    var id: String = "",
    var userId: String = "",
    var balance: Float = 0f
) {
    init {
        this.id = FirebaseDatabase.getInstance().reference.push().key ?: ""
    }
}
