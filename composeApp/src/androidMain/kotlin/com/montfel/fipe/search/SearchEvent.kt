package com.montfel.fipe.search

import com.montfel.fipe.ui.model.FormData

internal sealed interface SearchEvent {
    data class OnNavigateToForm(val formData: FormData) : SearchEvent
    data object OnNavigateBack : SearchEvent
    data class OnFipeCodeChanged(val fipeCode: String) : SearchEvent
    data object OnVehicleSearch : SearchEvent
}
