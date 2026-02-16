package com.montfel.fipe.ui.vehicledetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.montfel.fipe.data.model.SearchData
import com.montfel.fipe.data.service.VehicleDetailsService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

open class VehicleDetailsViewModel(
    private val searchData: SearchData,
    private val vehicleDetailsService: VehicleDetailsService
) : ViewModel() {
    private val _uiState = MutableStateFlow(VehicleDetailsUiState())
    val uiState = _uiState.asStateFlow()

    init {
        onVehicleSearch()
    }

    fun onVehicleSearch() {
        viewModelScope.launch {
            vehicleDetailsService.getVehicleInfo(searchData = searchData)
                .onSuccess { result ->
                    _uiState.update {
                        it.copy(
                            stateOfUi = VehicleDetailsStateOfUi.Success,
                            vehicleInfo = result
                        )
                    }

                }.onFailure {
                    _uiState.update {
                        it.copy(stateOfUi = VehicleDetailsStateOfUi.Error)
                    }
                }
        }
    }
}
