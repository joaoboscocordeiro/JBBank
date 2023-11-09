package com.example.core.data.repository

import com.example.core.domain.model.User
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject
import kotlin.coroutines.suspendCoroutine

/**
 * Created by João Bosco on 06/11/2023.
 */
class AuthFirebaseDataSourceImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : AuthFirebaseDataSource {

    override suspend fun login(email: String, password: String) {
        return suspendCoroutine { continuation ->
            firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    continuation.resumeWith(Result.success(Unit))
                } else {
                    task.exception?.let { error ->
                        continuation.resumeWith(Result.failure(error))
                    }
                }
            }
        }
    }

    override suspend fun register(
        name: String,
        email: String,
        phone: String,
        password: String
    ): User {
        return suspendCoroutine { continuation ->
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val userId = task.result.user?.uid ?: ""
                        val user = User(
                            id = userId,
                            name = name,
                            email = email,
                            phone = phone,
                            password = password
                        )
                        continuation.resumeWith(Result.success(user))
                    } else {
                        task.exception?.let { error ->
                            continuation.resumeWith(Result.failure(error))
                        }
                    }
                }
        }
    }

    override suspend fun recovery(email: String) {
        return suspendCoroutine { continuation ->
            firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    continuation.resumeWith(Result.success(Unit))
                } else {
                    task.exception?.let { error ->
                        continuation.resumeWith(Result.failure(error))
                    }
                }
            }
        }
    }
}