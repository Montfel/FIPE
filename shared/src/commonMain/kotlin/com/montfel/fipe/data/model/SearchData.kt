package com.montfel.fipe.data.model

import kotlinx.serialization.Serializable

@Serializable
data class SearchData(
    val referenceTable: String,
    val vehicleType: String,
    val brand: String,
    val model: String,
    val year: String,
    val fuelType: String,
    val searchType: String
)
