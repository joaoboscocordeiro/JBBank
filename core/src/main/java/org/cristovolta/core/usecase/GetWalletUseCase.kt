package org.cristovolta.core.usecase

import org.cristovolta.core.data.repository.WalletRepository
import org.cristovolta.core.domain.model.Wallet
import javax.inject.Inject

/**
 * Created by João Bosco on 09/11/2023.
 */
class GetWalletUseCase @Inject constructor(
    private val walletRepository: WalletRepository
) {
    suspend operator fun invoke(): Wallet {
        return walletRepository.getWallet()
    }
}