package com.montfel.fipe.data.service

import com.montfel.fipe.data.createHttpClient
import com.montfel.fipe.data.model.Brand
import com.montfel.fipe.data.model.CarInfo
import com.montfel.fipe.data.model.Model2
import com.montfel.fipe.data.model.Models
import com.montfel.fipe.data.model.Reference
import com.montfel.fipe.data.model.YearModel
import io.ktor.client.call.body
import io.ktor.client.request.parameter
import io.ktor.client.request.post

internal class ServiceImpl : Service {
    override suspend fun getReferenceTable(): List<Reference> {
        val httpClient = createHttpClient() //fixme
        val url = BASE_URL.plus(REFERENCE_TABLE)
        val response = httpClient.post(url)
        return response.body<List<Reference>>()
    }

    override suspend fun getBrands(referenceTable: Int, vehicleType: Int): List<Brand> {
        val httpClient = createHttpClient() //fixme
        val url = BASE_URL.plus(BRAND)
        val response = httpClient.post(url) {
            parameter(REFERENCE_TABLE_PARAMETER, referenceTable)
            parameter(VEHICLE_TYPE_PARAMETER, vehicleType)
        }
        return response.body<List<Brand>>()
    }

    override suspend fun getModels(referenceTable: Int, vehicleType: Int, brand: Int): Models {
        val httpClient = createHttpClient() //fixme
        val url = BASE_URL.plus(MODEL)
        val response = httpClient.post(url) {
            parameter(REFERENCE_TABLE_PARAMETER, referenceTable)
            parameter(VEHICLE_TYPE_PARAMETER, vehicleType)
            parameter(BRAND_PARAMETER, brand)
        }
        return response.body<Models>()
    }

    override suspend fun getYearModels(
        referenceTable: Int,
        vehicleType: Int,
        brand: Int,
        model: Int
    ): List<YearModel> {
        val httpClient = createHttpClient() //fixme
        val url = BASE_URL.plus(YEAR_MODEL)
        val response = httpClient.post(url) {
            parameter(REFERENCE_TABLE_PARAMETER, referenceTable)
            parameter(VEHICLE_TYPE_PARAMETER, vehicleType)
            parameter(BRAND_PARAMETER, brand)
            parameter(MODEL_PARAMETER, model)
        }
        return response.body<List<YearModel>>()
    }

    override suspend fun getModelByYear(
        referenceTable: Int,
        vehicleType: Int,
        brand: Int,
        model: Int,
        year: String,
        fuelType: Int
    ): List<Model2> {
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
        return response.body<List<Model2>>()
    }

    override suspend fun getCarInfo(
        referenceTable: Int,
        vehicleType: Int,
        brand: Int,
        model: Int,
        year: String,
        fuelType: Int
    ): CarInfo {
        val httpClient = createHttpClient() //fixme
        val url = BASE_URL.plus(CAR_INFO)
        val response = httpClient.post(url) {
            parameter(REFERENCE_TABLE_PARAMETER, referenceTable)
            parameter(VEHICLE_TYPE_PARAMETER, vehicleType)
            parameter(BRAND_PARAMETER, brand)
            parameter(MODEL_PARAMETER, model)
            parameter(YEAR_MODEL_PARAMETER, year)
            parameter(FUEL_TYPE_PARAMETER, fuelType)
        }
        return response.body<CarInfo>()
    }

    private companion object {
        const val BASE_URL: String = "https://veiculos.fipe.org.br/api/veiculos"
        const val REFERENCE_TABLE = "/ConsultarTabelaDeReferencia"
        const val BRAND = "/ConsultarMarcas"
        const val MODEL = "/ConsultarModelos"
        const val YEAR_MODEL = "/ConsultarAnoModelo"
        const val MODEL_BY_YEAR = "/ConsultarModelosAtravesDoAno"
        const val CAR_INFO = "/ConsultarValorComTodosParametros"
        const val REFERENCE_TABLE_PARAMETER = "codigoTabelaReferencia"
        const val VEHICLE_TYPE_PARAMETER = "codigoTipoVeiculo"
        const val BRAND_PARAMETER = "codigoMarca"
        const val MODEL_PARAMETER = "codigoModelo"
        const val YEAR_MODEL_PARAMETER = "anoModelo"
        const val FUEL_TYPE_PARAMETER = "codigoTipoCombustivel"
    }
}


