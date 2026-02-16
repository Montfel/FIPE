package com.montfel.fipe.data.model

import kotlinx.serialization.Serializable

@Serializable
data class SearchData(
    val vehicleType: String,
    val referenceTable: String,
    val brand: String? = null,
    val model: String? = null,
    val fipeCode: String? = null,
    val year: String,
    val fuelType: String,
    val searchType: String
)
