package com.montfel.fipe.domain.model

import kotlinx.collections.immutable.PersistentList

data class Models(
    val models: PersistentList<Model>,
    val yearModels: PersistentList<YearModel>,
)
