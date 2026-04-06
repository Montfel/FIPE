package com.montfel.fipe.ui.vehicledetails

import com.montfel.fipe.domain.model.VehicleInfo
import kotlinx.collections.immutable.ImmutableList

data class VehicleDetailsUiState(
    val stateOfUi: VehicleDetailsStateOfUi = VehicleDetailsStateOfUi.Loading,
    val vehiclesInfo: ImmutableList<VehicleInfo>? = null,
    val difference: String? = null
)

sealed interface VehicleDetailsStateOfUi {
    data object Loading : VehicleDetailsStateOfUi
    data object Success : VehicleDetailsStateOfUi
    data object Error : VehicleDetailsStateOfUi
}
