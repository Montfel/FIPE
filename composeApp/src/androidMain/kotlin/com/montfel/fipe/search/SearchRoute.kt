package com.montfel.fipe.search

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.montfel.fipe.components.ErrorScreen
import com.montfel.fipe.components.LoadingScreen
import com.montfel.fipe.domain.model.SearchRequest
import com.montfel.fipe.domain.model.VehicleType
import com.montfel.fipe.ui.model.FormData
import com.montfel.fipe.ui.search.SearchStateOfUi
import com.montfel.fipe.ui.search.SearchViewModel
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
internal fun SearchRoute(
    isByFipe: Boolean,
    onNavigateToForm: (formData: FormData) -> Unit,
    onNavigateToVehicleDetails: (searchRequest: SearchRequest) -> Unit,
    onNavigateBack: () -> Unit,
    viewModel: SearchViewModel = koinViewModel { parametersOf(isByFipe) },
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    when (uiState.stateOfUi) {
        SearchStateOfUi.Error -> {
            ErrorScreen(onRetry = viewModel::fetchReferenceTable)
        }

        SearchStateOfUi.Loading -> {
            LoadingScreen()
        }

        SearchStateOfUi.Success -> {
            SearchScreen(
                isByFipe = isByFipe,
                uiState = uiState,
                onEvent = { event ->
                    when (event) {
                        is SearchEvent.OnNavigateToForm -> {
                            onNavigateToForm(event.formData)
                        }

                        is SearchEvent.OnBrandSelected -> {
                            viewModel.onBrandSelected(event.brand)
                        }

                        is SearchEvent.OnReferenceSelected -> {
                            viewModel.onReferenceSelected(event.reference)
                        }

                        is SearchEvent.OnVehicleTypeSelected -> {
                            viewModel.onVehicleTypeSelected(event.vehicleType)
                        }

                        is SearchEvent.OnModelSelected -> {
                            viewModel.onModelSelected(event.model)
                        }

                        is SearchEvent.OnYearModelSelected -> {
                            viewModel.onYearModelSelected(event.yearModel)
                        }

                        SearchEvent.OnVehicleSearch -> {
                            onNavigateToVehicleDetails(
                                SearchRequest(
                                    referenceTable = uiState.selectedReference?.value.orEmpty(),
                                    vehicleType = VehicleType.fromCode(uiState.selectedVehicleType?.value.orEmpty()),
                                    brand = uiState.selectedBrand?.value,
                                    model = uiState.selectedModel?.value,
                                    fipeCode = null, //fixme
                                    year = uiState.selectedYearModel?.value?.dropLast(2).orEmpty(),
                                    fuelType = uiState.selectedYearModel?.value?.takeLast(1)
                                        .orEmpty(),
                                    searchType = if (isByFipe) "codigo" else "tradicional"
                                )
                            )
                        }

                        SearchEvent.OnNavigateBack -> {
                            onNavigateBack()
                        }
                    }
                }
            )
        }
    }
}
