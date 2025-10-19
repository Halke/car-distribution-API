package com.halks.distribution_erp.CarModel.dto;

import com.halks.distribution_erp.CarEngine.dto.CarEngineSummary;

import java.util.List;

public record CarModelResponse(
    Long id,
    String name,
    CarModelBrandSummary brand,
    List<CarEngineSummary> engines
) { }
