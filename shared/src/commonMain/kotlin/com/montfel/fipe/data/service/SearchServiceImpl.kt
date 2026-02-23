package com.montfel.fipe.data.service

import com.montfel.fipe.data.createHttpClient
import com.montfel.fipe.data.model.BrandData
import com.montfel.fipe.data.model.ModelsData
import com.montfel.fipe.data.model.ReferenceData
import com.montfel.fipe.data.model.YearModelData
import io.ktor.client.call.body
import io.ktor.client.request.parameter
import io.ktor.client.request.post

internal class SearchServiceImpl : SearchService {
    override suspend fun getReferenceTable(): List<ReferenceData> {
        val httpClient = createHttpClient() //fixme
        val response = httpClient.post(REFERENCE_TABLE)
        return response.body<List<ReferenceData>>()
    }

    override suspend fun getBrands(
        referenceTable: String,
        vehicleType: String
    ): List<BrandData> {
        val httpClient = createHttpClient() //fixme
        val response = httpClient.post(BRAND) {
            parameter(REFERENCE_TABLE_PARAMETER, referenceTable)
            parameter(VEHICLE_TYPE_PARAMETER, vehicleType)
        }
        return response.body<List<BrandData>>()
    }

    override suspend fun getModels(
        referenceTable: String,
        vehicleType: String,
        brand: String
    ): ModelsData {
        val httpClient = createHttpClient() //fixme
        val response = httpClient.post(MODEL) {
            parameter(REFERENCE_TABLE_PARAMETER, referenceTable)
            parameter(VEHICLE_TYPE_PARAMETER, vehicleType)
            parameter(BRAND_PARAMETER, brand)
        }
        return response.body<ModelsData>()
    }

    override suspend fun getYearModels(
        referenceTable: String,
        vehicleType: String,
        brand: String,
        model: String
    ): List<YearModelData> {
        val httpClient = createHttpClient() //fixme
        val response = httpClient.post(YEAR_MODEL) {
            parameter(REFERENCE_TABLE_PARAMETER, referenceTable)
            parameter(VEHICLE_TYPE_PARAMETER, vehicleType)
            parameter(BRAND_PARAMETER, brand)
            parameter(MODEL_PARAMETER, model)
        }
        return response.body<List<YearModelData>>()
    }

    override suspend fun getYearModelsByFipeCode(
        referenceTable: String,
        vehicleType: String,
        fipeCode: String
    ): List<YearModelData> {
        val httpClient = createHttpClient() //fixme
        val response = httpClient.post(YEAR_MODEL_BY_FIPE_CODE) {
            parameter(REFERENCE_TABLE_PARAMETER, referenceTable)
            parameter(VEHICLE_TYPE_PARAMETER, vehicleType)
            parameter(FIPE_CODE_PARAMETER, fipeCode)
        }
        return response.body<List<YearModelData>>()
    }

    private companion object {
        const val REFERENCE_TABLE = "ConsultarTabelaDeReferencia"
        const val BRAND = "ConsultarMarcas"
        const val MODEL = "ConsultarModelos"
        const val YEAR_MODEL = "ConsultarAnoModelo"
        const val YEAR_MODEL_BY_FIPE_CODE = "ConsultarAnoModeloPeloCodigoFipe"
        const val REFERENCE_TABLE_PARAMETER = "codigoTabelaReferencia"
        const val VEHICLE_TYPE_PARAMETER = "codigoTipoVeiculo"
        const val BRAND_PARAMETER = "codigoMarca"
        const val MODEL_PARAMETER = "codigoModelo"
        const val FIPE_CODE_PARAMETER = "modeloCodigoExterno"
    }
}


