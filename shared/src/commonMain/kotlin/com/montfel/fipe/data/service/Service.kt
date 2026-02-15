package com.montfel.fipe.data.service

import com.montfel.fipe.data.model.Brand

interface Service {
    suspend fun getBrands(referenceTable: Int, vehicleType: Int): List<Brand>
}
