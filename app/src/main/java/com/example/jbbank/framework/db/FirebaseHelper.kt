package com.example.jbbank.framework.db

import com.example.jbbank.R
import com.google.firebase.auth.FirebaseAuth

/**
 * Created by João Bosco on 03/11/2023.
 */
object FirebaseHelper {

    fun isAuthenticated() = FirebaseAuth.getInstance().currentUser != null
    fun validError(error: String): Int {
        return when {
            error.contains("There is no user record corresponding to this identifier") ->
                R.string.account_not_registered_register_fragment

            error.contains("The email address is badly formatted") ->
                R.string.invalid_email_register_fragment

            error.contains("The password is invalid or the user does not have a password") ->
                R.string.invalid_password_register_fragment

            error.contains("The email address is already in use by another account") ->
                R.string.email_in_use_register_fragment

            error.contains("Password should be at least 6 characters") ->
                R.string.strong_password_register_fragment

            error.contains("We have blocked all requests from this device due to unusual activity. Try again later.") ->
                R.string.access_to_this_account_has_been_temporarily_disabled

            else ->
                R.string.error_generic
        }
    }

//    fun validError(error: String): String {
//        var message = ""
//        if (error.contains("There is no user record corresponding to this identifier")) {
//            message = "${R.string.account_not_registered_register_fragment}"
//        } else if (error.contains("The email address is badly formatted")) {
//            message = "Insira um e-mail válido."
//        } else if (error.contains("The password is invalid or the user does not have a password")) {
//            message = "Senha inválida, tente novamente."
//        } else if (error.contains("The email address is already in use by another account")) {
//            message = "Este e-mail já está em uso."
//        } else if (error.contains("Password should be at least 6 characters")) {
//            message = "Insira uma senha mais forte."
//        }
//        return message
//    }
}