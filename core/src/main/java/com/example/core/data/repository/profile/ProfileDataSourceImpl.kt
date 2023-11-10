package com.example.core.data.repository.profile

import com.example.core.domain.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import javax.inject.Inject
import kotlin.coroutines.suspendCoroutine

/**
 * Created by João Bosco on 09/11/2023.
 */
class ProfileDataSourceImpl @Inject constructor(
    firebaseDatabase: FirebaseDatabase,
    firebaseAuth: FirebaseAuth
) : ProfileDataSource {

    private val getUserId = firebaseAuth.currentUser?.uid ?: ""

    private val profileRef = firebaseDatabase.reference
        .child("profile")
        .child(getUserId)

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
}