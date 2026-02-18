package com.montfel.fipe.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.montfel.fipe.domain.repository.SearchRepository
import com.montfel.fipe.ui.model.FormDataItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

open class SearchViewModel(
    private val isByFipe: Boolean,
    private val searchRepository: SearchRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(SearchUiState())
    val uiState = _uiState.asStateFlow()

    init {
        fetchReferenceTable()
    }

    fun fetchReferenceTable() {
        viewModelScope.launch {
            searchRepository.getReferenceTable()
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
            searchRepository.getBrands(
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
            searchRepository.getModels(
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
            searchRepository.getYearModels(
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
