package com.montfel.fipe.data.service

import com.montfel.fipe.data.createHttpClient
import com.montfel.fipe.data.model.SearchData
import com.montfel.fipe.data.model.VehicleInfo
import io.ktor.client.call.body
import io.ktor.client.request.parameter
import io.ktor.client.request.post

internal class VehicleDetailsServiceImpl : VehicleDetailsService {
    override suspend fun getVehicleInfo(searchData: SearchData): Result<VehicleInfo> {
        return runCatching {
            val httpClient = createHttpClient() //fixme
            val url = BASE_URL.plus(VEHICLE_INFO)
            val response = httpClient.post(url) {
                parameter(REFERENCE_TABLE_PARAMETER, searchData.referenceTable)
                parameter(VEHICLE_TYPE_PARAMETER, searchData.vehicleType)
                parameter(BRAND_PARAMETER, searchData.brand)
                parameter(MODEL_PARAMETER, searchData.model)
                parameter(YEAR_MODEL_PARAMETER, searchData.year)
                parameter(FUEL_TYPE_PARAMETER, searchData.fuelType)
                parameter(SEARCH_TYPE_PARAMETER, searchData.searchType)
                parameter(FIPE_CODE_PARAMETER, searchData.fipeCode)
            }
            response.body<VehicleInfo>()
        }
    }

    private companion object {
        const val BASE_URL: String = "https://veiculos.fipe.org.br/api/veiculos"
        const val VEHICLE_INFO = "/ConsultarValorComTodosParametros"
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


