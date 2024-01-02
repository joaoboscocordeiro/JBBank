package org.cristovolta.core.usecase

import org.cristovolta.core.data.repository.RechargeRepository
import org.cristovolta.core.domain.model.Recharge
import javax.inject.Inject

/**
 * Created by João Bosco on 01/12/2023.
 */
class GetRechargeUseCase @Inject constructor(
    private val rechargeRepository: RechargeRepository
) {
    suspend operator fun invoke(id: String): Recharge {
        return rechargeRepository.getRecharge(id)
    }
}