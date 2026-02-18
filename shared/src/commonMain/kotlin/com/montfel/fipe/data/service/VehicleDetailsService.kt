package com.montfel.fipe.data.service

import com.montfel.fipe.data.model.VehicleInfoData
import com.montfel.fipe.domain.model.SearchRequest

internal interface VehicleDetailsService {
    suspend fun getVehicleInfo(searchRequest: SearchRequest): VehicleInfoData
}
