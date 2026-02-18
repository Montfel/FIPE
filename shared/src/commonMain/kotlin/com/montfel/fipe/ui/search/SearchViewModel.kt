package com.montfel.fipe.ui.search

import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.montfel.fipe.domain.repository.SearchRepository
import com.montfel.fipe.ui.model.FormDataItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@Stable
open class SearchViewModel(
    private val isByFipe: Boolean,
    private val searchRepository: SearchRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(SearchUiState())
    val uiState = _uiState.asStateFlow()

    init {
        getReferenceTable()
    }

    fun getReferenceTable() {
        viewModelScope.launch {
            searchRepository.getReferenceTable()
                .onSuccess { references ->
                    val selectedReference = references.firstOrNull()?.let {
                        FormDataItem(
                            label = it.date,
                            value = it.code
                        )
                    }

                    _uiState.update {
                        it.copy(
                            stateOfUi = SearchStateOfUi.Success,
                            referenceTable = references,
                            selectedReference = selectedReference
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

    fun onReferenceSelected(reference: FormDataItem) {
        _uiState.update {
            it.copy(selectedReference = reference)
        }
    }

    fun onVehicleTypeSelected(vehicleType: FormDataItem) {
        _uiState.update {
            it.copy(
                selectedVehicleType = vehicleType,
                selectedBrand = null,
                selectedModel = null,
                selectedFipeCode = null,
                selectedYearModel = null
            )
        }

        if (!isByFipe) {
            getBrands()
        }
    }

    fun getBrands() {
        viewModelScope.launch {
            searchRepository.getBrands(
                referenceTable = uiState.value.selectedReference?.value.orEmpty(),
                vehicleType = uiState.value.selectedVehicleType?.value.orEmpty()
            ).onSuccess { brands ->
                _uiState.update {
                    it.copy(brands = brands)
                }
            }.onFailure {
                _uiState.update {
                    it.copy(stateOfUi = SearchStateOfUi.Error)
                }
            }
        }
    }

    fun onBrandSelected(brand: FormDataItem) {
        _uiState.update {
            it.copy(
                selectedBrand = brand,
                selectedModel = null,
                selectedFipeCode = null,
                selectedYearModel = null
            )
        }

        getModels()
    }

    fun getModels() {
        viewModelScope.launch {
            searchRepository.getModels(
                referenceTable = uiState.value.selectedReference?.value.orEmpty(),
                vehicleType = uiState.value.selectedVehicleType?.value.orEmpty(),
                brand = uiState.value.selectedBrand?.value.orEmpty(),
            ).onSuccess { models ->
                _uiState.update {
                    it.copy(models = models)
                }
            }.onFailure {
                _uiState.update {
                    it.copy(stateOfUi = SearchStateOfUi.Error)
                }
            }
        }
    }

    fun onModelSelected(model: FormDataItem) {
        _uiState.update {
            it.copy(
                selectedModel = model,
                selectedFipeCode = null,
                selectedYearModel = null
            )
        }

        getYearModels()
    }

    fun getYearModels() {
        viewModelScope.launch {
            searchRepository.getYearModels(
                referenceTable = uiState.value.selectedReference?.value.orEmpty(),
                vehicleType = uiState.value.selectedVehicleType?.value.orEmpty(),
                brand = uiState.value.selectedBrand?.value.orEmpty(),
                model = uiState.value.selectedModel?.value.orEmpty(),
            ).onSuccess { yearModels ->
                _uiState.update {
                    it.copy(yearModels = yearModels)
                }
            }.onFailure {
                _uiState.update {
                    it.copy(stateOfUi = SearchStateOfUi.Error)
                }
            }
        }
    }

    fun onFipeCodeChanged(fipeCode: String) {
        if (fipeCode.length <= 7 && fipeCode.all(Char::isDigit)) {
            _uiState.update {
                it.copy(
                    selectedFipeCode = fipeCode,
                    selectedYearModel = null,
                    hasFipeCodeError = false
                )
            }

            if (fipeCode.length == 7) {
                getYearModelsByFipeCode()
            }
        }
    }

    fun getYearModelsByFipeCode() {
        viewModelScope.launch {
            searchRepository.getYearModelsByFipeCode(
                referenceTable = uiState.value.selectedReference?.value.orEmpty(),
                vehicleType = uiState.value.selectedVehicleType?.value.orEmpty(),
                fipeCode = uiState.value.selectedFipeCode.orEmpty()
            ).onSuccess { yearModels ->
                _uiState.update {
                    it.copy(yearModels = yearModels)
                }
            }.onFailure {
                _uiState.update {
                    it.copy(hasFipeCodeError = true)
                }
            }
        }
    }

    fun onYearModelSelected(yearModel: FormDataItem) {
        _uiState.update {
            it.copy(selectedYearModel = yearModel)
        }
    }
}
