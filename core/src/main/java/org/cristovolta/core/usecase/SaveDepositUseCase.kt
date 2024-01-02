package org.cristovolta.core.usecase

import org.cristovolta.core.data.repository.DepositRepository
import org.cristovolta.core.domain.model.Deposit
import javax.inject.Inject

/**
 * Created by João Bosco on 13/11/2023.
 */
class SaveDepositUseCase @Inject constructor(
    private val depositRepository: DepositRepository
) {
    suspend operator fun invoke(deposit: Deposit): Deposit {
        return depositRepository.saveDeposit(deposit)
    }
}