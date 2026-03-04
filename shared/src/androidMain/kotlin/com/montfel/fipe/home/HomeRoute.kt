package com.montfel.fipe.home

import androidx.compose.runtime.Composable

@Composable
internal fun HomeRoute(
    onNavigateToSearch: (isByFipe: Boolean) -> Unit
) {
    HomeScreen(
        onEvent = { event ->
            when (event) {
                HomeEvent.OnSearchByFipeClicked -> onNavigateToSearch(true)
                HomeEvent.OnSearchByVehicleClicked -> onNavigateToSearch(false)
            }
        }
    )
}
