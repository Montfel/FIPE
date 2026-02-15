package com.montfel.fipe.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Brand(
    @SerialName("Label")
    val name: String,

    @SerialName("Value")
    val code: String,
)
