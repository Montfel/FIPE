package com.montfel.fipe.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class YearModelData(
    @SerialName("Label")
    val name: String,

    @SerialName("Value")
    val code: String,
)
