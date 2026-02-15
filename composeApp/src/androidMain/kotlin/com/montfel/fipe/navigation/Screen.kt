package com.montfel.fipe.navigation

import androidx.navigation3.runtime.NavKey
import com.montfel.fipe.data.model.SearchData
import com.montfel.fipe.ui.model.FormData
import kotlinx.serialization.Serializable

@Serializable
internal sealed interface Screen : NavKey {
    @Serializable
    data object Home : Screen

    @Serializable
    data object Search : Screen

    @Serializable
    data class Form(val formData: FormData) : Screen

    @Serializable
    data class VehicleDetails(val searchData: SearchData) : Screen
}
