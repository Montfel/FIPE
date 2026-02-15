package com.montfel.fipe.data.service

import com.montfel.fipe.data.createHttpClient
import com.montfel.fipe.data.model.Brand
import com.montfel.fipe.data.model.Model2
import com.montfel.fipe.data.model.Models
import com.montfel.fipe.data.model.Reference
import com.montfel.fipe.data.model.YearModel
import io.ktor.client.call.body
import io.ktor.client.request.parameter
import io.ktor.client.request.post

internal class SearchServiceImpl : SearchService {
    override suspend fun getReferenceTable(): Result<List<Reference>> {
        return runCatching {
            val httpClient = createHttpClient() //fixme
            val url = BASE_URL.plus(REFERENCE_TABLE)
            val response = httpClient.post(url)
            response.body<List<Reference>>()
        }
    }

    override suspend fun getBrands(
        referenceTable: String,
        vehicleType: String
    ): Result<List<Brand>> {
        return runCatching {
            val httpClient = createHttpClient() //fixme
            val url = BASE_URL.plus(BRAND)
            val response = httpClient.post(url) {
                parameter(REFERENCE_TABLE_PARAMETER, referenceTable)
                parameter(VEHICLE_TYPE_PARAMETER, vehicleType)
            }
            response.body<List<Brand>>()
        }
    }

    override suspend fun getModels(
        referenceTable: String,
        vehicleType: String,
        brand: String
    ): Result<Models> {
        return runCatching {
            val httpClient = createHttpClient() //fixme
            val url = BASE_URL.plus(MODEL)
            val response = httpClient.post(url) {
                parameter(REFERENCE_TABLE_PARAMETER, referenceTable)
                parameter(VEHICLE_TYPE_PARAMETER, vehicleType)
                parameter(BRAND_PARAMETER, brand)
            }
            response.body<Models>()
        }
    }

    override suspend fun getYearModels(
        referenceTable: String,
        vehicleType: String,
        brand: String,
        model: String
    ): Result<List<YearModel>> {
        return runCatching {
            val httpClient = createHttpClient() //fixme
            val url = BASE_URL.plus(YEAR_MODEL)
            val response = httpClient.post(url) {
                parameter(REFERENCE_TABLE_PARAMETER, referenceTable)
                parameter(VEHICLE_TYPE_PARAMETER, vehicleType)
                parameter(BRAND_PARAMETER, brand)
                parameter(MODEL_PARAMETER, model)
            }
            response.body<List<YearModel>>()
        }
    }

    override suspend fun getModelByYear(
        referenceTable: String,
        vehicleType: String,
        brand: String,
        model: String,
        year: String,
        fuelType: String
    ): Result<List<Model2>> {
        return runCatching {
            val httpClient = createHttpClient() //fixme
            val url = BASE_URL.plus(MODEL_BY_YEAR)
            val response = httpClient.post(url) {
                parameter(REFERENCE_TABLE_PARAMETER, referenceTable)
                parameter(VEHICLE_TYPE_PARAMETER, vehicleType)
                parameter(BRAND_PARAMETER, brand)
                parameter(MODEL_PARAMETER, model)
                parameter(YEAR_MODEL_PARAMETER, year)
                parameter(FUEL_TYPE_PARAMETER, fuelType)
            }
            response.body<List<Model2>>()
        }
    }

    private companion object {
        const val BASE_URL: String = "https://veiculos.fipe.org.br/api/veiculos"
        const val REFERENCE_TABLE = "/ConsultarTabelaDeReferencia"
        const val BRAND = "/ConsultarMarcas"
        const val MODEL = "/ConsultarModelos"
        const val YEAR_MODEL = "/ConsultarAnoModelo"
        const val MODEL_BY_YEAR = "/ConsultarModelosAtravesDoAno"
        const val REFERENCE_TABLE_PARAMETER = "codigoTabelaReferencia"
        const val VEHICLE_TYPE_PARAMETER = "codigoTipoVeiculo"
        const val BRAND_PARAMETER = "codigoMarca"
        const val MODEL_PARAMETER = "codigoModelo"
        const val YEAR_MODEL_PARAMETER = "anoModelo"
        const val FUEL_TYPE_PARAMETER = "codigoTipoCombustivel"
    }
}


