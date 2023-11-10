package com.example.core.usecase

import com.example.core.data.repository.wallet.WalletDataSourceImpl
import com.example.core.domain.model.Wallet
import javax.inject.Inject

/**
 * Created by João Bosco on 09/11/2023.
 */
class GetWalletUseCase @Inject constructor(
    private val walletDataSourceImpl: WalletDataSourceImpl
) {
    suspend operator fun invoke(): Wallet {
        return walletDataSourceImpl.getWallet()
    }
}