package com.montfel.fipe.home

internal sealed interface HomeEvent {
    object OnSearchByVehicleClicked : HomeEvent
    object OnSearchByFipeClicked : HomeEvent
}
