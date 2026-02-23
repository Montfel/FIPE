package com.montfel.fipe.data

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

internal fun createHttpClient(): HttpClient {
    return HttpClient {
        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {
                    println("KTOR_LOG: $message")
                }
            }
            level = LogLevel.BODY
        }

        install(ContentNegotiation) {
            json(
                json = Json {
                    ignoreUnknownKeys = true
                }
            )
        }

        defaultRequest {
            url("https://veiculos.fipe.org.br/api/veiculos/")
        }
    }
}
