package com.montfel.fipe.di

import org.jetbrains.compose.resources.getString
import kotlinx.coroutines.runBlocking
import org.jetbrains.compose.resources.StringResource

object StringsHelper {
    fun get(resource: StringResource): String {
        return runBlocking {
            getString(resource)
        }
    }
}
