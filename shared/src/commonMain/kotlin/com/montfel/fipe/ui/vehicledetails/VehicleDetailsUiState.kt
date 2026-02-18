package com.montfel.fipe.ui.vehicledetails

import androidx.compose.runtime.Stable
import com.montfel.fipe.domain.model.VehicleInfo

@Stable
data class VehicleDetailsUiState(
    val stateOfUi: VehicleDetailsStateOfUi = VehicleDetailsStateOfUi.Loading,
    val vehicleInfo: VehicleInfo? = null
)

sealed interface VehicleDetailsStateOfUi {
    data object Loading : VehicleDetailsStateOfUi
    data object Success : VehicleDetailsStateOfUi
    data object Error : VehicleDetailsStateOfUi
}
