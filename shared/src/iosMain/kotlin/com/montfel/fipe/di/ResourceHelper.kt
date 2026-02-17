package com.montfel.fipe.di

import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.addressOf
import kotlinx.cinterop.usePinned
import kotlinx.coroutines.runBlocking
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.getDrawableResourceBytes
import org.jetbrains.compose.resources.getSystemResourceEnvironment
import platform.Foundation.NSData
import platform.Foundation.dataWithBytes
import platform.UIKit.UIImage

object ResourceHelper {
    fun getString(resource: StringResource): String {
        return runBlocking {
            org.jetbrains.compose.resources.getString(resource)
        }
    }

    @OptIn(ExperimentalForeignApi::class)
    fun getDrawable(resource: DrawableResource): UIImage? {
        return runBlocking {
            val bytes = getDrawableResourceBytes(
                environment = getSystemResourceEnvironment(),
                resource = resource
            )

            UIImage.imageWithData(bytes.toNSData())
        }
    }

    @OptIn(ExperimentalForeignApi::class)
    fun ByteArray.toNSData(): NSData {
        return usePinned { pinned ->
            NSData.dataWithBytes(
                bytes = pinned.addressOf(0), // Pega o endereço do primeiro elemento
                length = this.size.toULong() // Converte o tamanho para o tipo esperado pelo iOS
            )
        }
    }
}
