package com.montfel.fipe.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.montfel.fipe.ui.home.HomeViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
internal fun HomeRoute(
    onNavigateToSearch: (isByFipe: Boolean) -> Unit,
    viewModel: HomeViewModel = koinViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    HomeScreen(
        uiState = uiState,
        onEvent = { event ->
            when (event) {
                HomeEvent.OnSearchByFipeClicked -> onNavigateToSearch(true)
                HomeEvent.OnSearchByVehicleClicked -> onNavigateToSearch(false)
            }
        }
    )
}
