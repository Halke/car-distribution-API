package com.halks.distribution_erp.CarEngine.dto;

import com.halks.distribution_erp.CarBrand.dto.CarBrandSummary;

public record CarEngineModelSummary(
        Long id,
        String name,
        CarBrandSummary brand
) {
}
