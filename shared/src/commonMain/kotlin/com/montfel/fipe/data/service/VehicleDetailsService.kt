package com.montfel.fipe.data.service

import com.montfel.fipe.data.model.SearchData
import com.montfel.fipe.data.model.VehicleInfo

interface VehicleDetailsService {
    suspend fun getVehicleInfo(searchData: SearchData): Result<VehicleInfo>
}
