package com.montfel.fipe.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Model2(
    @SerialName("Label")
    val name: String,

    @SerialName("Value")
    val code: String,
)
