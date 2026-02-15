package com.montfel.fipe

internal sealed interface HomeEvent {
    object OnSearchClicked : HomeEvent
}
