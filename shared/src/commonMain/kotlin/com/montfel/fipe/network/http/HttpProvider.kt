package com.montfel.fipe.network.http

import io.ktor.client.HttpClient

interface HttpProvider {
    suspend operator fun invoke(): HttpClient
}
