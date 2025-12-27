package com.halks.distribution_erp.UserCar.dto;

public record UserCarRequest(
        String color,
        String vin,
        Integer year,
        Integer mileageKm,
        Long carModelId,
        Long carEngineId
) { }
