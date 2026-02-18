package com.montfel.fipe.data.service

import com.montfel.fipe.data.model.BrandData
import com.montfel.fipe.data.model.ModelsData
import com.montfel.fipe.data.model.ReferenceData
import com.montfel.fipe.data.model.YearModelData

internal interface SearchService {
    suspend fun getReferenceTable(): List<ReferenceData>
    suspend fun getBrands(referenceTable: String, vehicleType: String): List<BrandData>
    suspend fun getModels(
        referenceTable: String,
        vehicleType: String,
        brand: String
    ): ModelsData

    suspend fun getYearModels(
        referenceTable: String,
        vehicleType: String,
        brand: String,
        model: String
    ): List<YearModelData>

    suspend fun getYearModelsByFipeCode(
        referenceTable: String,
        vehicleType: String,
        fipeCode: String
    ): List<YearModelData>
}
