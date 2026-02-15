package com.montfel.fipe.data.di

import com.montfel.fipe.data.service.Service
import com.montfel.fipe.data.service.ServiceImpl
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val dataModule = module {
    singleOf(::ServiceImpl) bind Service::class
}
