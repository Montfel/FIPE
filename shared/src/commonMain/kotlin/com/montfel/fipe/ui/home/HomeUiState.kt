package com.montfel.fipe.ui.home

import com.montfel.fipe.domain.model.VehicleInfo

data class HomeUiState(
    val recentSearches: List<VehicleInfo>? = null,
)
