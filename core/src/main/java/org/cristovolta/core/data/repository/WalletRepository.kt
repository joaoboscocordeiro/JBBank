package org.cristovolta.core.data.repository

import org.cristovolta.core.domain.model.Wallet

/**
 * Created by João Bosco on 09/11/2023.
 */
interface WalletRepository {
    suspend fun initWallet(wallet: Wallet)
    suspend fun getWallet(): Wallet
}