package com.montfel.fipe.data.di

import com.montfel.fipe.data.repository.SearchRepositoryImpl
import com.montfel.fipe.data.repository.VehicleDetailsRepositoryImpl
import com.montfel.fipe.data.service.SearchService
import com.montfel.fipe.data.service.SearchServiceImpl
import com.montfel.fipe.data.service.VehicleDetailsService
import com.montfel.fipe.data.service.VehicleDetailsServiceImpl
import com.montfel.fipe.domain.repository.SearchRepository
import com.montfel.fipe.domain.repository.VehicleDetailsRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val dataModule = module {
    singleOf(::SearchServiceImpl) bind SearchService::class
    singleOf(::VehicleDetailsServiceImpl) bind VehicleDetailsService::class
    singleOf(::SearchRepositoryImpl) bind SearchRepository::class
    singleOf(::VehicleDetailsRepositoryImpl) bind VehicleDetailsRepository::class
}
