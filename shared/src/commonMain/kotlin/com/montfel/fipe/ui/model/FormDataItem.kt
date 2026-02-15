package com.montfel.fipe.ui.model

import kotlinx.serialization.Serializable

@Serializable
data class FormDataItem(
    val label: String,
    val value: String
)
