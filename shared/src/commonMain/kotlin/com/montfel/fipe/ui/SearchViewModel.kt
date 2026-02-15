package com.montfel.fipe.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.montfel.fipe.data.service.Service
import com.montfel.fipe.ui.model.FormDataItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

open class SearchViewModel(
    private val service: Service
) : ViewModel() {
    private val _uiState = MutableStateFlow(SearchUiState())
    val uiState = _uiState.asStateFlow()

    init {
        fetchReferenceTable()
    }

    private fun fetchReferenceTable() {
        viewModelScope.launch {
            service.getReferenceTable()
                .onSuccess { result ->
                    _uiState.update {
                        it.copy(
                            stateOfUi = SearchStateOfUi.Success,
                            referenceTable = result
                        )
                    }
                }
                .onFailure {
                    _uiState.update {
                        it.copy(stateOfUi = SearchStateOfUi.Error)
                    }
                }
        }
    }

    fun onVehicleTypeSelected(vehicleType: FormDataItem) {
        _uiState.update {
            it.copy(selectedVehicleType = vehicleType)
        }
    }

    fun onReferenceSelected(reference: FormDataItem) {
        viewModelScope.launch {
            service.getBrands(
                referenceTable = reference.value,
                vehicleType = uiState.value.selectedVehicleType?.value.orEmpty()
            ).onSuccess { result ->
                _uiState.update {
                    it.copy(
                        selectedReference = reference,
                        brands = result
                    )
                }
            }.onFailure {
                _uiState.update {
                    it.copy(stateOfUi = SearchStateOfUi.Error)
                }
            }
        }
    }

    fun onBrandSelected(brand: FormDataItem) {
        viewModelScope.launch {
            service.getModels(
                referenceTable = uiState.value.selectedReference?.value.orEmpty(),
                vehicleType = uiState.value.selectedVehicleType?.value.orEmpty(),
                brand = brand.value
            ).onSuccess { result ->
                _uiState.update {
                    it.copy(
                        selectedBrand = brand,
                        models = result
                    )
                }
            }.onFailure {
                _uiState.update {
                    it.copy(stateOfUi = SearchStateOfUi.Error)
                }
            }
        }
    }

    fun onModelSelected(model: FormDataItem) {
        viewModelScope.launch {
            service.getYearModels(
                referenceTable = uiState.value.selectedReference?.value.orEmpty(),
                vehicleType = uiState.value.selectedVehicleType?.value.orEmpty(),
                brand = uiState.value.selectedBrand?.value.orEmpty(),
                model = model.value
            ).onSuccess { result ->
                _uiState.update {
                    it.copy(
                        selectedModel = model,
                        yearModels = result
                    )
                }
            }.onFailure {
                _uiState.update {
                    it.copy(stateOfUi = SearchStateOfUi.Error)
                }
            }
        }
    }

    fun onYearModelSelected(yearModel: FormDataItem) {
        _uiState.update {
            it.copy(
                selectedYearModel = yearModel
            )
        }
    }

    fun onVehicleSearch() {
        viewModelScope.launch {
            service.getVehicleInfo(
                referenceTable = uiState.value.selectedReference?.value.orEmpty(),
                vehicleType = uiState.value.selectedVehicleType?.value.orEmpty(),
                brand = uiState.value.selectedBrand?.value.orEmpty(),
                model = uiState.value.selectedModel?.value.orEmpty(),
                year = uiState.value.selectedYearModel?.value?.dropLast(2).orEmpty(),
                fuelType = uiState.value.selectedYearModel?.value?.takeLast(1).orEmpty(),
                searchType = "tradicional"
            ).onSuccess { result ->
                println("===> $result")
            }.onFailure {
                _uiState.update {
                    it.copy(stateOfUi = SearchStateOfUi.Error)
                }
            }
        }
    }
}
