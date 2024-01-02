package org.cristovolta.core.usecase

import org.cristovolta.core.data.repository.WalletRepository
import org.cristovolta.core.domain.model.Wallet
import javax.inject.Inject

/**
 * Created by João Bosco on 09/11/2023.
 */
class InitWalletUseCase @Inject constructor(
    private val walletRepository: WalletRepository
) {
    suspend operator fun invoke(wallet: Wallet) {
        return walletRepository.initWallet(wallet)
    }
}