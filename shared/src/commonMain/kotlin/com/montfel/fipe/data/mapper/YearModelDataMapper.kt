package com.montfel.fipe.data.mapper

import com.montfel.fipe.data.model.YearModelData
import com.montfel.fipe.domain.model.YearModel

internal fun YearModelData.toYearModel(): YearModel {
    return YearModel(
        code = code,
        name = name
    )
}
