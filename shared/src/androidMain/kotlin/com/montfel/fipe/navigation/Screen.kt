package com.montfel.fipe.navigation

import androidx.navigation3.runtime.NavKey
import com.montfel.fipe.domain.model.SearchRequest
import com.montfel.fipe.ui.model.FormData
import kotlinx.serialization.Serializable

@Serializable
internal sealed interface Screen : NavKey {
    @Serializable
    data object Home : Screen

    @Serializable
    data class Search(val isByFipe: Boolean) : Screen

    @Serializable
    data class Form(val formData: FormData) : Screen

    @Serializable
    data class VehicleDetails(val searchRequest: SearchRequest) : Screen
}
