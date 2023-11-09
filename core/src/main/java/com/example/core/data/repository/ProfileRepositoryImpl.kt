package com.example.core.data.repository

import com.example.core.domain.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import javax.inject.Inject
import kotlin.coroutines.suspendCoroutine

/**
 * Created by João Bosco on 09/11/2023.
 */
class ProfileRepositoryImpl @Inject constructor(
    firebaseDatabase: FirebaseDatabase
) : ProfileRepository {

    private val profileRef = firebaseDatabase.reference
        .child("profile")
        .child(getUserId())

    override suspend fun saveProfile(user: User) {
        return suspendCoroutine { continuation ->
            profileRef.setValue(user).addOnCompleteListener { task ->
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

    private fun getUserId() = FirebaseAuth.getInstance().currentUser?.uid ?: ""
}