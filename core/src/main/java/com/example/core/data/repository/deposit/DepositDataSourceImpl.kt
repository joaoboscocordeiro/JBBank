package com.example.core.data.repository.deposit

import com.example.core.domain.model.Deposit
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ServerValue
import com.google.firebase.database.ValueEventListener
import javax.inject.Inject
import kotlin.coroutines.suspendCoroutine

/**
 * Created by João Bosco on 13/11/2023.
 */
class DepositDataSourceImpl @Inject constructor(
    database: FirebaseDatabase,
    firebaseAuth: FirebaseAuth
) : DepositDataSource {

    private val getUserId = firebaseAuth.currentUser?.uid ?: ""

    private val depositRef = database.reference
        .child("deposit")
        .child(getUserId)

    override suspend fun saveDeposit(deposit: Deposit): Deposit {
        return suspendCoroutine { continuation ->
            depositRef
                .child(deposit.id)
                .setValue(deposit).addOnCompleteListener { task ->
                    if (task.isSuccessful) {

                        val dateRef = depositRef
                            .child(deposit.id)
                            .child("date")
                        dateRef.setValue(ServerValue.TIMESTAMP)
                            .addOnCompleteListener { taskUpdate ->
                                if (taskUpdate.isSuccessful) {
                                    continuation.resumeWith(Result.success(deposit))
                                } else {
                                    taskUpdate.exception?.let { error ->
                                        continuation.resumeWith(Result.failure(error))
                                    }
                                }
                            }
                    } else {
                        task.exception?.let { error ->
                            continuation.resumeWith(Result.failure(error))
                        }
                    }
                }
        }
    }

    override suspend fun getDeposit(id: String): Deposit {
        return suspendCoroutine { continuation ->
            depositRef
                .child(id)
                .addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val deposit = snapshot.getValue(Deposit::class.java)
                        deposit?.let { continuation.resumeWith(Result.success(it)) }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        error.toException().let {
                            continuation.resumeWith(Result.failure(it))
                        }
                    }
                })
        }
    }
}