package com.montfel.fipe.ui.search

import com.montfel.fipe.domain.model.Brand
import com.montfel.fipe.domain.model.Models
import com.montfel.fipe.domain.model.Reference
import com.montfel.fipe.domain.model.YearModel
import com.montfel.fipe.ui.model.FormDataItem

data class SearchUiState(
    val stateOfUi: SearchStateOfUi = SearchStateOfUi.Loading,
    val referenceTable: List<Reference> = emptyList(),
    val brands: List<Brand> = emptyList(),
    val models: Models? = null,
    val yearModels: List<YearModel> = emptyList(),
    val selectedVehicleType: FormDataItem? = null,
    val selectedReference: FormDataItem? = null,
    val selectedBrand: FormDataItem? = null,
    val selectedModel: FormDataItem? = null,
    val selectedYearModel: FormDataItem? = null,
)

sealed interface SearchStateOfUi {
    data object Loading : SearchStateOfUi
    data object Success : SearchStateOfUi
    data object Error : SearchStateOfUi
}
