package com.montfel.fipe.data.service

import com.montfel.fipe.data.model.Brand
import com.montfel.fipe.data.model.Model2
import com.montfel.fipe.data.model.Models
import com.montfel.fipe.data.model.Reference
import com.montfel.fipe.data.model.VehicleInfo
import com.montfel.fipe.data.model.YearModel

interface Service {
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

    suspend fun getModelByYear(
        referenceTable: String,
        vehicleType: String,
        brand: String,
        model: String,
        year: String,
        fuelType: String
    ): Result<List<Model2>>

    suspend fun getVehicleInfo(
        referenceTable: String,
        vehicleType: String,
        brand: String,
        model: String,
        year: String,
        fuelType: String,
        searchType: String
    ): Result<VehicleInfo>
}
