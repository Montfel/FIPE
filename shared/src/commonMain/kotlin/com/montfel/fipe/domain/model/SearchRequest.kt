package com.montfel.fipe.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class SearchRequest(
    val vehicleType: VehicleType,
    val referenceTable: String,
    val brand: String? = null,
    val model: String? = null,
    val fipeCode: String? = null,
    val year: String,
    val fuelType: String,
    val searchType: String
)
