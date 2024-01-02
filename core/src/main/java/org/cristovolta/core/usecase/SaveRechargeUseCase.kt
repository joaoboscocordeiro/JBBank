package org.cristovolta.core.usecase

import org.cristovolta.core.data.repository.RechargeRepository
import org.cristovolta.core.domain.model.Recharge
import javax.inject.Inject

/**
 * Created by João Bosco on 01/12/2023.
 */
class SaveRechargeUseCase @Inject constructor(
    private val rechargeRepository: RechargeRepository
) {
    suspend operator fun invoke(recharge: Recharge): Recharge {
        return rechargeRepository.saveRecharge(recharge)
    }
}