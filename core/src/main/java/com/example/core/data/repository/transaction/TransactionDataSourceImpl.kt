package com.example.core.data.repository.transaction

import com.example.core.domain.model.Transaction
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ServerValue
import com.google.firebase.database.ValueEventListener
import javax.inject.Inject
import kotlin.coroutines.suspendCoroutine

/**
 * Created by João Bosco on 14/11/2023.
 */
class TransactionDataSourceImpl @Inject constructor(
    firebaseDatabase: FirebaseDatabase,
    firebaseAuth: FirebaseAuth
) : TransactionDataSource {

    private val getUserId = firebaseAuth.currentUser?.uid ?: ""

    private val transactionRef = firebaseDatabase.reference
        .child("transaction")
        .child(getUserId)

    override suspend fun saveTransaction(transaction: Transaction) {
        return suspendCoroutine { continuation ->
            transactionRef
                .child(transaction.id)
                .setValue(transaction).addOnCompleteListener { task ->
                    if (task.isSuccessful) {

                        val dateRef = transactionRef
                            .child(transaction.id)
                            .child("date")
                        dateRef.setValue(ServerValue.TIMESTAMP)

                        continuation.resumeWith(Result.success(Unit))
                    } else {
                        task.exception?.let { error ->
                            continuation.resumeWith(Result.failure(error))
                        }
                    }
                }
        }
    }

    override suspend fun getTransactions(): List<Transaction> {
        return suspendCoroutine { continuation ->
            transactionRef.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val transactions = mutableListOf<Transaction>()
                    for (ds in snapshot.children) {
                        val transaction = ds.getValue(Transaction::class.java)
                        transaction?.let { transactions.add(it) }
                    }
                    continuation.resumeWith(Result.success(transactions))
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