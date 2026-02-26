package com.montfel.fipe.ui.model

import kotlinx.serialization.Serializable

@Serializable
data class FormData(
    val title: String,
    val items: List<FormDataItem>,
    val type: FormDataType
)
