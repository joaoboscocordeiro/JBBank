package com.example.core.data.repository.wallet

import com.example.core.domain.model.Wallet
import com.google.firebase.database.FirebaseDatabase
import javax.inject.Inject
import kotlin.coroutines.suspendCoroutine

/**
 * Created by João Bosco on 09/11/2023.
 */
class WalletDataSourceImpl @Inject constructor(
    private val database: FirebaseDatabase
) : WalletDataSource {

    private val walletRef = database.reference
        .child("wallet")

    override suspend fun initWallet(wallet: Wallet) {
        return suspendCoroutine { continuation ->
            walletRef.child(wallet.id).setValue(wallet).addOnCompleteListener { task ->
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