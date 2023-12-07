package com.example.core.data.repository.recharge

import com.example.core.domain.model.Recharge

/**
 * Created by João Bosco on 01/12/2023.
 */
interface RechargeDataSource {
    suspend fun saveRecharge(recharge: Recharge): Recharge
    suspend fun getRecharge(id: String): Recharge
}