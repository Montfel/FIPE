package com.montfel.fipe.home

internal sealed interface HomeEvent {
    object OnSearchClicked : HomeEvent
}
