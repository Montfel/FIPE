package com.montfel.fipe.data.mapper

import com.montfel.fipe.data.model.ModelData
import com.montfel.fipe.domain.model.Model

internal fun ModelData.toModel(): Model {
    return Model(
        code = code.toString(),
        name = name
    )
}
