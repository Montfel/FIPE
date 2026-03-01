package com.montfel.fipe.ui.vehicledetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.montfel.fipe.domain.model.SearchRequest
import com.montfel.fipe.domain.repository.VehicleDetailsRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.math.abs
import kotlin.math.roundToInt

open class VehicleDetailsViewModel(
    private val searchRequest: SearchRequest,
    private val vehicleDetailsRepository: VehicleDetailsRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(VehicleDetailsUiState())
    val uiState = _uiState.asStateFlow()

    init {
        getVehicleDetails()
    }

    fun getVehicleDetails() {
        viewModelScope.launch {
            val currentVehicleInfoDeferred = async {
                vehicleDetailsRepository.getVehicleInfo(searchRequest = searchRequest)
            }
            val lastMonthVehicleInfoDeferred = async {
                vehicleDetailsRepository.getVehicleInfo(
                    searchRequest = searchRequest.copy(
                        referenceTable = searchRequest.referenceTable.toInt().dec().toString()
                    )
                )
            }

            val currentVehicleInfo = currentVehicleInfoDeferred.await()
            val lastMonthVehicleInfo = lastMonthVehicleInfoDeferred.await()

            currentVehicleInfo.onSuccess { currentVehicleInfo ->
                _uiState.update {
                    it.copy(
                        stateOfUi = VehicleDetailsStateOfUi.Success,
                        currentVehicleInfo = currentVehicleInfo
                    )
                }

                lastMonthVehicleInfo.onSuccess { lastMonthVehicleInfo ->
                    val difference = calculateDifference(
                        currentPrice = currentVehicleInfo.price.convertStringMoneyToDouble(),
                        lastPrice = lastMonthVehicleInfo.price.convertStringMoneyToDouble()
                    )

                    _uiState.update {
                        it.copy(difference = difference.formatDifference())
                    }
                }
            }
        }
    }

    private fun calculateDifference(currentPrice: Double, lastPrice: Double): Double {
        if (lastPrice == 0.0 && currentPrice == 0.0) return 0.0
        if (lastPrice == 0.0) return if (currentPrice > 0) Double.POSITIVE_INFINITY else Double.NEGATIVE_INFINITY

        val difference = ((currentPrice - lastPrice) / abs(lastPrice)) * 100.0
        val roundedDifference = (difference * 100).roundToInt().toDouble().div(100)

        return roundedDifference
    }

    private fun String.convertStringMoneyToDouble(): Double {
        val value = this
            .replace("R$", "")
            .replace("\\s".toRegex(), "")
            .replace(".", "")
            .replace(",", ".")

        return value.toDoubleOrNull() ?: 0.0
    }

    private fun Double.formatDifference(): String {
        val isPositive = this >= 0.0

        var value = this
            .toString()
            .replace(".", ",")
        value = "$value% no último mês"

        return if (isPositive) "+$value" else value
    }
}
