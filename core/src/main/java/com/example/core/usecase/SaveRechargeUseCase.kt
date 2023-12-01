package com.example.core.usecase

import com.example.core.data.repository.recharge.RechargeDataSourceImpl
import com.example.core.domain.model.Recharge
import javax.inject.Inject

/**
 * Created by João Bosco on 01/12/2023.
 */
class SaveRechargeUseCase @Inject constructor(
    private val rechargeDataSourceImpl: RechargeDataSourceImpl
) {
    suspend operator fun invoke(recharge: Recharge): Recharge {
        return rechargeDataSourceImpl.saveRecharge(recharge)
    }
}