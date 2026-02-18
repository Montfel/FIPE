package com.montfel.fipe.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class ModelsData(
    @SerialName("Modelos")
    val models: List<ModelData>,

    @SerialName("Anos")
    val yearModels: List<YearModelData>,
)


