package com.montfel.fipe.search

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.montfel.fipe.components.ErrorScreen
import com.montfel.fipe.components.LoadingScreen
import com.montfel.fipe.domain.model.SearchRequest
import com.montfel.fipe.domain.model.VehicleType
import com.montfel.fipe.ui.model.FormData
import com.montfel.fipe.ui.model.FormDataItem
import com.montfel.fipe.ui.model.FormDataType
import com.montfel.fipe.ui.model.FormDataType.BRAND
import com.montfel.fipe.ui.model.FormDataType.MODEL
import com.montfel.fipe.ui.model.FormDataType.REFERENCE
import com.montfel.fipe.ui.model.FormDataType.VEHICLE_TYPE
import com.montfel.fipe.ui.model.FormDataType.YEAR_MODEL
import com.montfel.fipe.ui.search.SearchStateOfUi
import com.montfel.fipe.ui.search.SearchViewModel
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
internal fun SearchRoute(
    isByFipe: Boolean,
    formDataItem: FormDataItem?,
    formDataType: FormDataType?,
    onNavigateToForm: (formData: FormData) -> Unit,
    onNavigateToVehicleDetails: (searchRequest: SearchRequest) -> Unit,
    onNavigateBack: () -> Unit,
    viewModel: SearchViewModel = koinViewModel { parametersOf(isByFipe) },
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = formDataItem, key2 = formDataType) {
        println("===> formDataItem: $formDataItem")
        println("===> formDataType: $formDataType")
        if (formDataType != null && formDataItem != null) {
            when (formDataType) {
                REFERENCE -> viewModel.onReferenceSelected(formDataItem)
                VEHICLE_TYPE -> viewModel.onVehicleTypeSelected(formDataItem)
                BRAND -> viewModel.onBrandSelected(formDataItem)
                MODEL -> viewModel.onModelSelected(formDataItem)
                YEAR_MODEL -> viewModel.onYearModelSelected(formDataItem)
            }
        }
    }

    when (uiState.stateOfUi) {
        SearchStateOfUi.Error -> {
            ErrorScreen(onRetry = viewModel::getReferenceTable)
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

                        SearchEvent.OnVehicleSearch -> {
                            onNavigateToVehicleDetails(
                                SearchRequest(
                                    referenceTable = uiState.selectedReference?.value.orEmpty(),
                                    vehicleType = VehicleType.fromCode(uiState.selectedVehicleType?.value.orEmpty()),
                                    brand = uiState.selectedBrand?.value,
                                    model = uiState.selectedModel?.value,
                                    fipeCode = uiState.selectedFipeCode,
                                    year = uiState.selectedYearModel?.value?.dropLast(2)
                                        .orEmpty(),
                                    fuelType = uiState.selectedYearModel?.value?.takeLast(1)
                                        .orEmpty(),
                                    searchType = if (isByFipe) "codigo" else "tradicional"
                                )
                            )
                        }

                        SearchEvent.OnNavigateBack -> {
                            onNavigateBack()
                        }

                        is SearchEvent.OnFipeCodeChanged -> {
                            viewModel.onFipeCodeChanged(event.fipeCode)
                        }
                    }
                }
            )
        }
    }
}
