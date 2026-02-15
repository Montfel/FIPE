package com.montfel.fipe.data.service

import com.montfel.fipe.data.createHttpClient
import com.montfel.fipe.data.model.Brand
import io.ktor.client.call.body
import io.ktor.client.request.parameter
import io.ktor.client.request.post

internal class ServiceImpl : Service {
    override suspend fun getBrands(referenceTable: Int, vehicleType: Int): List<Brand> {
        val httpClient = createHttpClient() //fixme
        val url = BASE_URL.plus(BRAND)
        val response = httpClient.post(url) {
            parameter(REFERENCE_TABLE_PARAMETER, referenceTable)
            parameter(VEHICLE_TYPE_PARAMETER, vehicleType)
        }
        return response.body<List<Brand>>()
    }

    private companion object {
        const val BASE_URL: String = "https://veiculos.fipe.org.br/api/veiculos"
        const val BRAND = "/ConsultarMarcas"
        const val REFERENCE_TABLE_PARAMETER = "codigoTabelaReferencia"
        const val VEHICLE_TYPE_PARAMETER = "codigoTipoVeiculo"
    }
}


