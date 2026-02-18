package com.montfel.fipe.data.mapper

import com.montfel.fipe.data.model.BrandData
import com.montfel.fipe.domain.model.Brand

internal fun BrandData.toBrand(): Brand {
    return Brand(
        code = code,
        name = name
    )
}
