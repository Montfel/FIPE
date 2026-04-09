package com.montfel.fipe.ui.di

import com.montfel.fipe.ui.home.HomeViewModel
import com.montfel.fipe.ui.search.SearchViewModel
import com.montfel.fipe.ui.vehicledetails.VehicleDetailsViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val uiModule = module {
    viewModelOf(::HomeViewModel)
    viewModelOf(::SearchViewModel)
    viewModelOf(::VehicleDetailsViewModel)
}
