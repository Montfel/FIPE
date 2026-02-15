package com.montfel.fipe

sealed interface HomeEvent {
    object OnSearchClicked : HomeEvent
}
