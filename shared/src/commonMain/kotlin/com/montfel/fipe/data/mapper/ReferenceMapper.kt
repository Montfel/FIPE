package com.montfel.fipe.data.mapper

import com.montfel.fipe.data.model.ReferenceData
import com.montfel.fipe.domain.model.Reference

internal fun ReferenceData.toReference(): Reference {
    return Reference(
        code = code.toString(),
        date = date
    )
}
