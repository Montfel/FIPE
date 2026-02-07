package com.montfel.fipe

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform