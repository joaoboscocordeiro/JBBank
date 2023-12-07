package com.example.core.domain.model

import android.os.Parcelable
import com.google.firebase.database.FirebaseDatabase
import kotlinx.parcelize.Parcelize

/**
 * Created by João Bosco on 01/12/2023.
 */
@Parcelize
data class Recharge(
    var id: String = "",
    val date: Long = 0L,
    val amount: Float = 0f,
    val number: String = ""
) : Parcelable {
    init {
        this.id = FirebaseDatabase.getInstance().reference.push().key ?: ""
    }
}