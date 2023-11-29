package com.example.core.domain.model

import com.google.firebase.database.Exclude

/**
 * Created by João Bosco on 03/11/2023.
 */
data class User(
    val id: String = "",
    var name: String = "",
    val email: String = "",
    var phone: String = "",
    @get:Exclude
    val password: String = ""
)
