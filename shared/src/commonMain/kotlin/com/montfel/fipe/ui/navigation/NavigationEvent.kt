package com.montfel.fipe.ui.navigation

sealed interface NavigationEvent {
    object GoToSearch : NavigationEvent
}
