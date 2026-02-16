package com.montfel.fipe.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.montfel.fipe.data.service.SearchService
import com.montfel.fipe.ui.model.FormDataItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

open class SearchViewModel(
    private val isByFipe: Boolean,
    private val searchService: SearchService
) : ViewModel() {
    private val _uiState = MutableStateFlow(SearchUiState())
    val uiState = _uiState.asStateFlow()

    init {
        fetchReferenceTable()
    }

    fun fetchReferenceTable() {
        viewModelScope.launch {
            searchService.getReferenceTable()
                .onSuccess { references ->
                    _uiState.update {
                        it.copy(
                            stateOfUi = SearchStateOfUi.Success,
                            referenceTable = references
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
            searchService.getBrands(
                referenceTable = reference.value,
                vehicleType = uiState.value.selectedVehicleType?.value.orEmpty()
            ).onSuccess { brands ->
                _uiState.update {
                    it.copy(
                        selectedReference = reference,
                        brands = brands
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
            searchService.getModels(
                referenceTable = uiState.value.selectedReference?.value.orEmpty(),
                vehicleType = uiState.value.selectedVehicleType?.value.orEmpty(),
                brand = brand.value
            ).onSuccess { models ->
                _uiState.update {
                    it.copy(
                        selectedBrand = brand,
                        models = models
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
            searchService.getYearModels(
                referenceTable = uiState.value.selectedReference?.value.orEmpty(),
                vehicleType = uiState.value.selectedVehicleType?.value.orEmpty(),
                brand = uiState.value.selectedBrand?.value.orEmpty(),
                model = model.value
            ).onSuccess { yearModels ->
                _uiState.update {
                    it.copy(
                        selectedModel = model,
                        yearModels = yearModels
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
}
