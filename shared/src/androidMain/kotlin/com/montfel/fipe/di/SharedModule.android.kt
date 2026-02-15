package com.montfel.fipe.di

import com.montfel.fipe.ui.HomeViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

actual val platformModule = module {
    viewModelOf(::HomeViewModel)
}
