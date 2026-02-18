package com.montfel.fipe.ui.search

import androidx.compose.runtime.Stable
import com.montfel.fipe.domain.model.Brand
import com.montfel.fipe.domain.model.Models
import com.montfel.fipe.domain.model.Reference
import com.montfel.fipe.domain.model.YearModel
import com.montfel.fipe.ui.model.FormDataItem
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf

@Stable
data class SearchUiState(
    val stateOfUi: SearchStateOfUi = SearchStateOfUi.Loading,
    val referenceTable: PersistentList<Reference> = persistentListOf(),
    val brands: PersistentList<Brand> = persistentListOf(),
    val models: Models? = null,
    val yearModels: PersistentList<YearModel> = persistentListOf(),
    val selectedVehicleType: FormDataItem? = null,
    val selectedReference: FormDataItem? = null,
    val selectedBrand: FormDataItem? = null,
    val selectedModel: FormDataItem? = null,
    val selectedFipeCode: String? = null,
    val selectedYearModel: FormDataItem? = null,
)

sealed interface SearchStateOfUi {
    data object Loading : SearchStateOfUi
    data object Success : SearchStateOfUi
    data object Error : SearchStateOfUi
}
