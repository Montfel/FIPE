package com.montfel.fipe.domain.repository

import com.montfel.fipe.domain.model.Brand
import com.montfel.fipe.domain.model.Models
import com.montfel.fipe.domain.model.Reference
import com.montfel.fipe.domain.model.YearModel

interface SearchRepository {
    suspend fun getReferenceTable(): Result<List<Reference>>
    suspend fun getBrands(referenceTable: String, vehicleType: String): Result<List<Brand>>
    suspend fun getModels(
        referenceTable: String,
        vehicleType: String,
        brand: String
    ): Result<Models>

    suspend fun getYearModels(
        referenceTable: String,
        vehicleType: String,
        brand: String,
        model: String
    ): Result<List<YearModel>>

    suspend fun getYearModelsByFipeCode(
        referenceTable: String,
        vehicleType: String,
        fipeCode: String
    ): Result<List<YearModel>>
}
