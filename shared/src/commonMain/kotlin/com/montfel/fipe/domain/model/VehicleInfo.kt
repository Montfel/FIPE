package com.montfel.fipe.domain.model

data class VehicleInfo(
    val price: String,
    val brand: String,
    val model: String,
    val yearModel: Int,
    val fuel: String,
    val fipeCode: String,
    val referenceMonth: String,
    val authentication: String,
    val vehicleType: VehicleType,
    val fuelAcronym: String,
    val consultDate: String
)
