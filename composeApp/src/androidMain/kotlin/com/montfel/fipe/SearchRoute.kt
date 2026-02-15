package com.montfel.fipe

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.montfel.fipe.ui.SearchStateOfUi
import com.montfel.fipe.ui.SearchViewModel
import org.koin.compose.viewmodel.koinViewModel
import com.montfel.fipe.ui.model.FormData

@Composable
internal fun SearchRoute(
    onNavigateToForm: (formData: FormData) -> Unit,
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
                            viewModel.onVehicleSearch()
                        }
                    }
                }
            )
        }
    }
}
