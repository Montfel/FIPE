package com.montfel.fipe.domain.model

enum class VehicleType(val code: String) {
    CAR("1"),
    MOTORCYCLE("2"),
    TRUCK("3");

    companion object {
        fun fromCode(code: String): VehicleType {
            return entries.find { it.code == code } ?: CAR
        }
    }
}
