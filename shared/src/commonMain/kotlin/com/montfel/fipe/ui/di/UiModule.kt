package com.montfel.fipe.ui.di

import com.montfel.fipe.ui.SearchViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val uiModule = module {
    viewModelOf(::SearchViewModel)
}
