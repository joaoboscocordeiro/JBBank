package com.example.core.domain.model

import android.os.Parcelable
import com.google.firebase.database.FirebaseDatabase
import kotlinx.parcelize.Parcelize

/**
 * Created by João Bosco on 13/11/2023.
 */
@Parcelize
data class Deposit(
    var id: String = "",
    val date: Long = 0,
    val amount: Float = 0f
) : Parcelable {
    init {
        this.id = FirebaseDatabase.getInstance().reference.push().key ?: ""
    }
}
