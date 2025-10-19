package com.halks.distribution_erp.CarBrand.dto;

import com.halks.distribution_erp.CarModel.dto.CarModelSummary;

import java.util.List;

public record CarBrandResponse(
        Long id,
        String name,
        String country,
        List<CarModelSummary> models
) { }
