package com.montfel.fipe.di

import com.montfel.fipe.data.di.dataModule
import com.montfel.fipe.network.di.networkModule
import com.montfel.fipe.ui.di.uiModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {
      config?.invoke(this)
      modules(
          dataModule,
          uiModule,
          networkModule
      )
    }
}
