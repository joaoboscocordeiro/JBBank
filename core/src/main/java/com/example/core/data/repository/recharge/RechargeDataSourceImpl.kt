package com.example.core.data.repository.recharge

import com.example.core.domain.model.Recharge
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ServerValue
import javax.inject.Inject
import kotlin.coroutines.suspendCoroutine

/**
 * Created by João Bosco on 01/12/2023.
 */
class RechargeDataSourceImpl @Inject constructor(
    database: FirebaseDatabase,
    firebaseAuth: FirebaseAuth
) : RechargeDataSource {

    private val getUserId = firebaseAuth.currentUser?.uid ?: ""

    private val rechargeRef = database.reference
        .child("recharge")
        .child(getUserId)

    override suspend fun saveRecharge(recharge: Recharge): Recharge {
        return suspendCoroutine { continuation ->
            rechargeRef
                .child(recharge.id)
                .setValue(recharge).addOnCompleteListener { task ->
                    if (task.isSuccessful) {

                        val dateRef = rechargeRef
                            .child(recharge.id)
                            .child("date")
                        dateRef.setValue(ServerValue.TIMESTAMP)
                            .addOnCompleteListener { taskUpdate ->
                                if (taskUpdate.isSuccessful) {
                                    continuation.resumeWith(Result.success(recharge))
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

    override suspend fun getRecharge(id: String): Recharge {
        TODO("Not yet implemented")
    }
}