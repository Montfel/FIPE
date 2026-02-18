package com.montfel.fipe.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class ReferenceData(
    @SerialName("Codigo")
    val code: Int,

    @SerialName("Mes")
    val date: String,
)
