package com.montfel.fipe.ui.model

import androidx.compose.runtime.Stable
import kotlinx.collections.immutable.PersistentList
import kotlinx.serialization.Serializable

@Stable
@Serializable
data class FormData(
    val title: String,
    val items: PersistentList<FormDataItem>,
    val onItemClick: (item: FormDataItem) -> Unit
)
