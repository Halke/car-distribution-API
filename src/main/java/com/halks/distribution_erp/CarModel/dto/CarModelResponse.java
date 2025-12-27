package com.halks.distribution_erp.CarModel.dto;

public record CarModelResponse(
    Long id,
    String name,
    CarModelBrandSummary brand
) { }
