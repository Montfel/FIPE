package com.montfel.fipe.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface Screen: NavKey {
    @Serializable
    data object Home: Screen

    @Serializable
    data object Search: Screen
}
