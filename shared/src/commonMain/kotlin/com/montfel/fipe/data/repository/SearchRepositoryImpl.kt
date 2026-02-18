package com.montfel.fipe.data.repository

import com.montfel.fipe.data.mapper.toBrand
import com.montfel.fipe.data.mapper.toModels
import com.montfel.fipe.data.mapper.toReference
import com.montfel.fipe.data.mapper.toYearModel
import com.montfel.fipe.data.model.BrandData
import com.montfel.fipe.data.model.ReferenceData
import com.montfel.fipe.data.model.YearModelData
import com.montfel.fipe.data.service.SearchService
import com.montfel.fipe.domain.model.Brand
import com.montfel.fipe.domain.model.Models
import com.montfel.fipe.domain.model.Reference
import com.montfel.fipe.domain.model.YearModel
import com.montfel.fipe.domain.repository.SearchRepository
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.toPersistentList

internal class SearchRepositoryImpl(
    private val searchService: SearchService
) : SearchRepository {
    override suspend fun getReferenceTable(): Result<PersistentList<Reference>> {
        return runCatching {
            searchService.getReferenceTable()
                .map(ReferenceData::toReference)
                .toPersistentList()
        }
    }

    override suspend fun getBrands(
        referenceTable: String,
        vehicleType: String
    ): Result<PersistentList<Brand>> {
        return runCatching {
            searchService.getBrands(referenceTable, vehicleType)
                .map(BrandData::toBrand)
                .toPersistentList()
        }
    }

    override suspend fun getModels(
        referenceTable: String,
        vehicleType: String,
        brand: String
    ): Result<Models> {
        return runCatching {
            searchService.getModels(
                referenceTable = referenceTable,
                vehicleType = vehicleType,
                brand = brand
            ).toModels()
        }
    }

    override suspend fun getYearModels(
        referenceTable: String,
        vehicleType: String,
        brand: String,
        model: String
    ): Result<PersistentList<YearModel>> {
        return runCatching {
            searchService.getYearModels(
                referenceTable = referenceTable,
                vehicleType = vehicleType,
                brand = brand,
                model = model
            ).map(YearModelData::toYearModel)
                .toPersistentList()
        }
    }

    override suspend fun getYearModelsByFipeCode(
        referenceTable: String,
        vehicleType: String,
        fipeCode: String
    ): Result<PersistentList<YearModel>> {
        return runCatching {
            searchService.getYearModelsByFipeCode(
                referenceTable = referenceTable,
                vehicleType = vehicleType,
                fipeCode = fipeCode
            ).map(YearModelData::toYearModel)
                .toPersistentList()
        }
    }
}
