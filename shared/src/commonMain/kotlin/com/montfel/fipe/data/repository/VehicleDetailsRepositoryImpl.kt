package com.montfel.fipe.data.repository

import com.montfel.fipe.data.mapper.toVehicleInfoData
import com.montfel.fipe.data.service.VehicleDetailsService
import com.montfel.fipe.domain.model.SearchRequest
import com.montfel.fipe.domain.model.VehicleInfo
import com.montfel.fipe.domain.repository.VehicleDetailsRepository

internal class VehicleDetailsRepositoryImpl(
    private val vehicleDetailsService: VehicleDetailsService
) : VehicleDetailsRepository {
    override suspend fun getVehicleInfo(searchRequest: SearchRequest): Result<VehicleInfo> {
        return runCatching {
            vehicleDetailsService.getVehicleInfo(searchRequest = searchRequest).toVehicleInfoData()
        }
    }
}
