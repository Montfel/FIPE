package com.montfel.fipe.network.di

import com.montfel.fipe.network.http.HttpProvider
import com.montfel.fipe.network.http.HttpProviderImpl
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val networkModule = module {
    singleOf(::HttpProviderImpl) bind HttpProvider::class
}
