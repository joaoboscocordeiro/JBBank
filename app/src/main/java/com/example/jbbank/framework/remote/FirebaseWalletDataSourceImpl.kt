package com.example.jbbank.framework.remote

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import org.cristovolta.core.data.repository.WalletRepository
import org.cristovolta.core.domain.model.Wallet
import javax.inject.Inject
import kotlin.coroutines.suspendCoroutine

/**
 * Created by João Bosco on 09/11/2023.
 */
class FirebaseWalletDataSourceImpl @Inject constructor(
    database: FirebaseDatabase,
    firebaseAuth: FirebaseAuth
) : WalletRepository {

    private val getUserId = firebaseAuth.currentUser?.uid ?: ""

    private val walletRef = database.reference
        .child("wallet")

    override suspend fun initWallet(wallet: Wallet) {
        return suspendCoroutine { continuation ->
            walletRef.child(getUserId).setValue(wallet).addOnCompleteListener { task ->
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

    override suspend fun getWallet(): Wallet {
        return suspendCoroutine { continuation ->
            walletRef.child(getUserId)
            walletRef.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val wallet = snapshot.getValue(Wallet::class.java)
                    wallet?.let { continuation.resumeWith(Result.success(it)) }
                }

                override fun onCancelled(error: DatabaseError) {
                    continuation.resumeWith(Result.failure(error.toException()))
                }
            })
        }
    }
}