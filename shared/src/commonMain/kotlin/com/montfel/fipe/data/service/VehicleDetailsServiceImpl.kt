package com.montfel.fipe.data.service

import com.montfel.fipe.data.model.VehicleInfoData
import com.montfel.fipe.domain.model.SearchRequest
import com.montfel.fipe.network.http.HttpProvider
import io.ktor.client.call.body
import io.ktor.client.request.parameter
import io.ktor.client.request.post

internal class VehicleDetailsServiceImpl(
    private val httpProvider: HttpProvider
) : VehicleDetailsService {
    override suspend fun getVehicleInfo(searchRequest: SearchRequest): VehicleInfoData {
        val response = httpProvider().post(VEHICLE_INFO) {
            parameter(REFERENCE_TABLE_PARAMETER, searchRequest.referenceTable)
            parameter(VEHICLE_TYPE_PARAMETER, searchRequest.vehicleType.code)
            parameter(BRAND_PARAMETER, searchRequest.brand)
            parameter(MODEL_PARAMETER, searchRequest.model)
            parameter(YEAR_MODEL_PARAMETER, searchRequest.year)
            parameter(FUEL_TYPE_PARAMETER, searchRequest.fuelType)
            parameter(SEARCH_TYPE_PARAMETER, searchRequest.searchType)
            parameter(FIPE_CODE_PARAMETER, searchRequest.fipeCode)
        }
        return response.body<VehicleInfoData>()
    }

    private companion object {
        const val VEHICLE_INFO = "ConsultarValorComTodosParametros"
        const val REFERENCE_TABLE_PARAMETER = "codigoTabelaReferencia"
        const val VEHICLE_TYPE_PARAMETER = "codigoTipoVeiculo"
        const val BRAND_PARAMETER = "codigoMarca"
        const val MODEL_PARAMETER = "codigoModelo"
        const val YEAR_MODEL_PARAMETER = "anoModelo"
        const val FUEL_TYPE_PARAMETER = "codigoTipoCombustivel"
        const val SEARCH_TYPE_PARAMETER = "tipoConsulta"
        const val FIPE_CODE_PARAMETER = "modeloCodigoExterno"
    }
}


