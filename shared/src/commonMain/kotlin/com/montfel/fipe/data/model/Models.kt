package com.montfel.fipe.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Models(
    @SerialName("Modelos")
    val models: List<Model>,

    @SerialName("Anos")
    val yearModels: List<YearModel>,
)


