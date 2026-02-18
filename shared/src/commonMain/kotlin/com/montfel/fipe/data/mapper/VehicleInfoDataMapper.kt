package com.montfel.fipe.data.mapper

import com.montfel.fipe.data.model.VehicleInfoData
import com.montfel.fipe.domain.model.VehicleInfo
import com.montfel.fipe.domain.model.VehicleType

internal fun VehicleInfoData.toVehicleInfoData(): VehicleInfo {
    return VehicleInfo(
        price = price,
        brand = brand,
        model = model,
        yearModel = yearModel,
        fuel = fuel,
        fipeCode = fipeCode,
        referenceMonth = referenceMonth,
        authentication = authentication,
        vehicleType = VehicleType.fromCode(vehicleType.toString()),
        fuelAcronym = fuelAcronym,
        consultDate = consultDate
    )
}
