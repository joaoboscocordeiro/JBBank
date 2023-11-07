package com.example.jbbank.framework.db

import com.google.firebase.auth.FirebaseAuth

/**
 * Created by João Bosco on 03/11/2023.
 */
class FirebaseHelper {

    companion object {
        fun isAuthenticated() = FirebaseAuth.getInstance().currentUser != null
    }

    fun validError(error: String): String {
        var message = ""
        if (error.contains("There is no user record corresponding to this identifier")) {
            message = "Nenhuma conta encontrada com este e-mail."
        } else if (error.contains("The email address is badly formatted")) {
            message = "Insira um e-mail válido."
        } else if (error.contains("The password is invalid or the user does not have a password")) {
            message = "Senha inválida, tente novamente."
        } else if (error.contains("The email address is already in use by another account")) {
            message = "Este e-mail já está em uso."
        } else if (error.contains("Password should be at least 6 characters")) {
            message = "Insira uma senha mais forte."
        }
        return message
    }
}