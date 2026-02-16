package com.montfel.fipe.search

import com.montfel.fipe.ui.model.FormData
import com.montfel.fipe.ui.model.FormDataItem

internal sealed interface SearchEvent {
    data class OnNavigateToForm(val formData: FormData) : SearchEvent
    data object OnNavigateBack : SearchEvent
    data class OnVehicleTypeSelected(val vehicleType: FormDataItem) : SearchEvent
    data class OnReferenceSelected(val reference: FormDataItem) : SearchEvent
    data class OnBrandSelected(val brand: FormDataItem) : SearchEvent
    data class OnModelSelected(val model: FormDataItem) : SearchEvent
    data class OnYearModelSelected(val yearModel: FormDataItem) : SearchEvent
    data object OnVehicleSearch : SearchEvent
}
