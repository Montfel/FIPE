package com.montfel.fipe.ui.vehicledetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.montfel.fipe.domain.model.SearchRequest
import com.montfel.fipe.domain.model.VehicleInfo
import com.montfel.fipe.domain.repository.VehicleDetailsRepository
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
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
            val vehiclesInfo = (0 until 6).map { index ->
                async {
                    val referenceTable = searchRequest.referenceTable.toInt() - index

                    vehicleDetailsRepository.getVehicleInfo(
                        searchRequest = searchRequest.copy(
                            referenceTable = referenceTable.toString()
                        )
                    )
                }
            }
                .awaitAll()
                .mapNotNull(Result<VehicleInfo>::getOrNull)

            val currentPrice = vehiclesInfo.firstOrNull()?.price
            val previousPrice = vehiclesInfo.getOrNull(1)?.price
            val hasDifference = currentPrice != null && previousPrice != null

            _uiState.update {
                it.copy(
                    stateOfUi = VehicleDetailsStateOfUi.Success,
                    vehiclesInfo = vehiclesInfo.toPersistentList(),
                    difference = if (hasDifference) {
                        calculateDifference(
                            currentPrice = currentPrice,
                            lastPrice = previousPrice
                        ).formatDifference()
                    } else {
                        null
                    }
                )
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

    private fun Double.formatDifference(): String {
        val isPositive = this >= 0.0

        var value = this
            .toString()
            .replace(".", ",")

        value = "$value% no último mês"

        return if (isPositive) "+$value" else value
    }
}
