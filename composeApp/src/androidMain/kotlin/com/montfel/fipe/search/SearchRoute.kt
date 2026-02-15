package com.montfel.fipe.search

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.montfel.fipe.data.model.SearchData
import com.montfel.fipe.ui.search.SearchStateOfUi
import org.koin.compose.viewmodel.koinViewModel
import com.montfel.fipe.ui.model.FormData
import com.montfel.fipe.ui.search.SearchViewModel

@Composable
internal fun SearchRoute(
    onNavigateToForm: (formData: FormData) -> Unit,
    onNavigateToVehicleDetails: (searchData: SearchData) -> Unit,
    viewModel: SearchViewModel = koinViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    when (uiState.stateOfUi) {
        SearchStateOfUi.Error -> {
            Column {
                Text("Error")
            }
        }

        SearchStateOfUi.Loading -> {
            Column {
                Text("Loading")
            }
        }

        SearchStateOfUi.Success -> {
            SearchScreen(
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
                                SearchData(
                                    referenceTable = uiState.selectedReference?.value.orEmpty(),
                                    vehicleType = uiState.selectedVehicleType?.value.orEmpty(),
                                    brand = uiState.selectedBrand?.value.orEmpty(),
                                    model = uiState.selectedModel?.value.orEmpty(),
                                    year = uiState.selectedYearModel?.value?.dropLast(2).orEmpty(),
                                    fuelType = uiState.selectedYearModel?.value?.takeLast(1).orEmpty(),
                                    searchType = "tradicional"
                                )
                            )
                        }
                    }
                }
            )
        }
    }
}
