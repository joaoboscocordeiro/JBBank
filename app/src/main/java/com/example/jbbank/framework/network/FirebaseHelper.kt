package com.example.jbbank.framework.network

import com.google.firebase.auth.FirebaseAuth

/**
 * Created by João Bosco on 03/11/2023.
 */
object FirebaseHelper {
    fun getAuth() = FirebaseAuth.getInstance()
    fun isAuthenticated() = getAuth().currentUser != null
    fun getUserId() = FirebaseAuth.getInstance().currentUser?.uid ?: ""
    fun validError(error: String): Int {
        return when {
            error.contains("There is no user record corresponding to this identifier") ->
                com.example.jbbank.R.string.account_not_registered_register_fragment

            error.contains("The email address is badly formatted") ->
                com.example.jbbank.R.string.invalid_email_register_fragment

            error.contains("The password is invalid or the user does not have a password") ->
                com.example.jbbank.R.string.invalid_password_register_fragment

            error.contains("The email address is already in use by another account") ->
                com.example.jbbank.R.string.email_in_use_register_fragment

            error.contains("Password should be at least 6 characters") ->
                com.example.jbbank.R.string.strong_password_register_fragment

            error.contains("We have blocked all requests from this device due to unusual activity. Try again later.") ->
                com.example.jbbank.R.string.access_to_this_account_has_been_temporarily_disabled

            else ->
                com.example.jbbank.R.string.error_generic
        }
    }
}