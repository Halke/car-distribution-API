package com.halks.distribution_erp.CarEngine.dto;

import com.halks.distribution_erp.CarEngine.enums.FuelType;

public record CarEngineSummary(
        Long id,
        String name,
        String manufacturer,
        FuelType fuelType,
        Integer productionStartYear,
        Integer productionEndYear
) { }
