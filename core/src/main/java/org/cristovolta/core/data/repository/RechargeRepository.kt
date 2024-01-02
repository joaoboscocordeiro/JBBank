package org.cristovolta.core.data.repository

import org.cristovolta.core.domain.model.Recharge

/**
 * Created by João Bosco on 01/12/2023.
 */
interface RechargeRepository {
    suspend fun saveRecharge(recharge: Recharge): Recharge
    suspend fun getRecharge(id: String): Recharge
}