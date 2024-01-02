package org.cristovolta.core.domain.model

/**
 * Created by João Bosco on 03/11/2023.
 */
data class User(
    val id: String = "",
    var name: String = "",
    val email: String = "",
    var phone: String = "",
    var image: String = "",
    //@get:Exclude
    val password: String = ""
)
