package com.montfel.fipe.data.mapper

import com.montfel.fipe.data.model.ModelData
import com.montfel.fipe.data.model.ModelsData
import com.montfel.fipe.data.model.YearModelData
import com.montfel.fipe.domain.model.Models
import kotlinx.collections.immutable.toPersistentList

internal fun ModelsData.toModels(): Models {
    return Models(
        models = models.map(ModelData::toModel).toPersistentList(),
        yearModels = yearModels.map(YearModelData::toYearModel).toPersistentList()
    )
}
