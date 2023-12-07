package com.example.core.usecase

import com.example.core.data.repository.recharge.RechargeDataSourceImpl
import com.example.core.domain.model.Recharge
import javax.inject.Inject

/**
 * Created by João Bosco on 01/12/2023.
 */
class GetRechargeUseCase @Inject constructor(
    private val rechargeDataSourceImpl: RechargeDataSourceImpl
) {
    suspend operator fun invoke(id: String): Recharge {
        return rechargeDataSourceImpl.getRecharge(id)
    }
}