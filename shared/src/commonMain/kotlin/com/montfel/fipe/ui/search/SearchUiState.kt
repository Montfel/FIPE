package com.montfel.fipe.ui.search

import com.montfel.fipe.domain.model.Brand
import com.montfel.fipe.domain.model.Models
import com.montfel.fipe.domain.model.Reference
import com.montfel.fipe.domain.model.YearModel
import com.montfel.fipe.ui.model.FormDataItem
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf

data class SearchUiState(
    val stateOfUi: SearchStateOfUi = SearchStateOfUi.Loading,
    val referenceTable: PersistentList<Reference> = persistentListOf(),
    val brands: PersistentList<Brand> = persistentListOf(),
    val models: Models? = null,
    val yearModels: PersistentList<YearModel> = persistentListOf(),
    val selectedReference: FormDataItem? = null,
    val selectedVehicleType: FormDataItem? = null,
    val selectedBrand: FormDataItem? = null,
    val selectedModel: FormDataItem? = null,
    val selectedFipeCode: String? = null,
    val selectedYearModel: FormDataItem? = null,
    val hasFipeCodeError: Boolean = false
) {
    val shouldEnableBrand: Boolean = selectedVehicleType != null
    val shouldEnableModel: Boolean = selectedVehicleType != null && selectedBrand != null
    val shouldEnableFipeCode: Boolean = selectedVehicleType != null
    val shouldEnableYearModel: Boolean =
        selectedVehicleType != null && ((selectedBrand != null && selectedModel != null) || (selectedFipeCode != null && selectedFipeCode.length == 7 && !hasFipeCodeError))
    val shouldEnableButton: Boolean =
        selectedVehicleType != null && ((selectedBrand != null && selectedModel != null) || selectedFipeCode != null) && selectedYearModel != null
}

sealed interface SearchStateOfUi {
    data object Loading : SearchStateOfUi
    data object Success : SearchStateOfUi
    data object Error : SearchStateOfUi
}
