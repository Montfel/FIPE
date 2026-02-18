package com.montfel.fipe.domain.repository

import com.montfel.fipe.domain.model.SearchRequest
import com.montfel.fipe.domain.model.VehicleInfo

interface VehicleDetailsRepository {
    suspend fun getVehicleInfo(searchRequest: SearchRequest): Result<VehicleInfo>
}
