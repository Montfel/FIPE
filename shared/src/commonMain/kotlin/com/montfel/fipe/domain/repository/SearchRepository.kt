package com.montfel.fipe.domain.repository

import com.montfel.fipe.domain.model.Brand
import com.montfel.fipe.domain.model.Models
import com.montfel.fipe.domain.model.Reference
import com.montfel.fipe.domain.model.YearModel
import kotlinx.collections.immutable.PersistentList

interface SearchRepository {
    suspend fun getReferenceTable(): Result<PersistentList<Reference>>
    suspend fun getBrands(referenceTable: String, vehicleType: String): Result<PersistentList<Brand>>
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
    ): Result<PersistentList<YearModel>>

    suspend fun getYearModelsByFipeCode(
        referenceTable: String,
        vehicleType: String,
        fipeCode: String
    ): Result<PersistentList<YearModel>>
}
