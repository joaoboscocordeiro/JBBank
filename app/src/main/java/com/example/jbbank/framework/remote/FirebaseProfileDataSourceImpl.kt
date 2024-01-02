package com.example.jbbank.framework.remote

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import org.cristovolta.core.data.repository.ProfileRepository
import org.cristovolta.core.domain.model.User
import javax.inject.Inject
import kotlin.coroutines.suspendCoroutine

/**
 * Created by João Bosco on 09/11/2023.
 */
class FirebaseProfileDataSourceImpl @Inject constructor(
    firebaseDatabase: FirebaseDatabase,
    firebaseAuth: FirebaseAuth
) : ProfileRepository {

    private val getUserId = firebaseAuth.currentUser?.uid ?: ""

    private val profileRef = firebaseDatabase.reference
        .child("profile")

    override suspend fun saveProfile(user: User) {
        return suspendCoroutine { continuation ->
            profileRef
                .child(getUserId)
                .setValue(user)
                .addOnCompleteListener { task ->
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

    override suspend fun getProfile(): User {
        return suspendCoroutine { continuation ->
            profileRef
                .child(getUserId)
                .addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val user = snapshot.getValue(User::class.java)
                        user?.let { continuation.resumeWith(Result.success(it)) }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        continuation.resumeWith(Result.failure(error.toException()))
                    }
                })
        }
    }

    override suspend fun getProfileList(): List<User> {
        return suspendCoroutine { continuation ->
            profileRef.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val userList: MutableList<User> = mutableListOf()

                    for (ds in snapshot.children) {
                        val user = ds.getValue(User::class.java)
                        user?.let { userList.add(it) }
                    }

                    continuation.resumeWith(Result.success(
                        userList.apply {
                            removeAll { it.id == getUserId }
                        }
                    ))
                }

                override fun onCancelled(error: DatabaseError) {
                    continuation.resumeWith(Result.failure(error.toException()))
                }
            })
        }
    }
}