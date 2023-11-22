package com.example.core.usecase

import com.example.core.data.repository.deposit.DepositDataSourceImpl
import com.example.core.domain.model.Deposit
import javax.inject.Inject

/**
 * Created by João Bosco on 13/11/2023.
 */
class GetDepositUseCase @Inject constructor(
    private val depositDataSourceImpl: DepositDataSourceImpl
) {
    suspend operator fun invoke(id: String): Deposit {
        return depositDataSourceImpl.getDeposit(id)
    }
}