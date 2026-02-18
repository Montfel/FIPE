package com.montfel.fipe.data.mapper

import com.montfel.fipe.data.model.ModelData
import com.montfel.fipe.data.model.ModelsData
import com.montfel.fipe.data.model.YearModelData
import com.montfel.fipe.domain.model.Models

internal fun ModelsData.toModels(): Models {
    return Models(
        models = models.map(ModelData::toModel),
        yearModels = yearModels.map(YearModelData::toYearModel)
    )
}
