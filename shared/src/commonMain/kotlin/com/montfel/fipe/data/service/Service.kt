package com.montfel.fipe.data.service

import com.montfel.fipe.data.model.Brand
import com.montfel.fipe.data.model.CarInfo
import com.montfel.fipe.data.model.Model2
import com.montfel.fipe.data.model.Models
import com.montfel.fipe.data.model.Reference
import com.montfel.fipe.data.model.YearModel

interface Service {
    suspend fun getReferenceTable(): List<Reference>
    suspend fun getBrands(referenceTable: Int, vehicleType: Int): List<Brand>
    suspend fun getModels(referenceTable: Int, vehicleType: Int, brand: Int): Models
    suspend fun getYearModels(
        referenceTable: Int,
        vehicleType: Int,
        brand: Int,
        model: Int
    ): List<YearModel>

    suspend fun getModelByYear(
        referenceTable: Int,
        vehicleType: Int,
        brand: Int,
        model: Int,
        year: String,
        fuelType: Int
    ): List<Model2>

    suspend fun getCarInfo(
        referenceTable: Int,
        vehicleType: Int,
        brand: Int,
        model: Int,
        year: String,
        fuelType: Int
    ): CarInfo
}
