package com.halks.distribution_erp.UserCar.dto;

import com.halks.distribution_erp.CarBrand.dto.CarBrandSummary;

public record UserCarModelSummary(
        Long id,
        String name,
        CarBrandSummary brand
) { }
