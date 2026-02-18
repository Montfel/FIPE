package com.montfel.fipe.vehicledetails

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.montfel.fipe.components.ErrorScreen
import com.montfel.fipe.components.LoadingScreen
import com.montfel.fipe.domain.model.SearchRequest
import com.montfel.fipe.ui.vehicledetails.VehicleDetailsStateOfUi
import com.montfel.fipe.ui.vehicledetails.VehicleDetailsViewModel
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
internal fun VehicleDetailsRoute(
    searchRequest: SearchRequest,
    viewModel: VehicleDetailsViewModel = koinViewModel { parametersOf(searchRequest) },
    onNavigateBack: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    when (uiState.stateOfUi) {
        VehicleDetailsStateOfUi.Error -> {
            ErrorScreen(onRetry = viewModel::onVehicleSearch)
        }

        VehicleDetailsStateOfUi.Loading -> {
            LoadingScreen()
        }

        VehicleDetailsStateOfUi.Success -> {
            VehicleDetailsScreen(
                uiState = uiState,
                onNavigateBack = onNavigateBack
            )
        }
    }
}
