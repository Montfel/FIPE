package com.montfel.fipe.data.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VehicleInfo(
    @SerialName("Valor")
    val price: String,

    @SerialName("Marca")
    val brand: String,

    @SerialName("Modelo")
    val model: String,

    @SerialName("AnoModelo")
    val yearModel: Int,

    @SerialName("Combustivel")
    val fuel: String,

    @SerialName("CodigoFipe")
    val fipeCode: String,

    @SerialName("MesReferencia")
    val referenceMonth: String,

    @SerialName("Autenticacao")
    val authentication: String,

    @SerialName("TipoVeiculo")
    val vehicleType: Int,

    @SerialName("SiglaCombustivel")
    val fuelAcronym: String,

    @SerialName("DataConsulta")
    val consultDate: String
)
