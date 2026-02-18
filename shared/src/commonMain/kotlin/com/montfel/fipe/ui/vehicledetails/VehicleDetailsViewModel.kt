package com.montfel.fipe.ui.vehicledetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.montfel.fipe.domain.model.SearchRequest
import com.montfel.fipe.domain.repository.VehicleDetailsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

open class VehicleDetailsViewModel(
    private val searchRequest: SearchRequest,
    private val vehicleDetailsRepository: VehicleDetailsRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(VehicleDetailsUiState())
    val uiState = _uiState.asStateFlow()

    init {
        onVehicleSearch()
    }

    fun onVehicleSearch() {
        viewModelScope.launch {
            vehicleDetailsRepository.getVehicleInfo(searchRequest = searchRequest)
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
