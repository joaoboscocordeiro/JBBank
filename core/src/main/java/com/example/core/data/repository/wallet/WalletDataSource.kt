package com.example.core.data.repository.wallet

import com.example.core.domain.model.Wallet

/**
 * Created by João Bosco on 09/11/2023.
 */
interface WalletDataSource {
    suspend fun initWallet(wallet: Wallet)
}