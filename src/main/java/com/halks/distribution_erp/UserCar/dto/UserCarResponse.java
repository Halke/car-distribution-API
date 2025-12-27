package com.halks.distribution_erp.UserCar.dto;

import com.halks.distribution_erp.CarEngine.dto.CarEngineSummary;
import com.halks.distribution_erp.User.dto.UserSummary;

public record UserCarResponse(
        Long id,
        String color,
        String vin,
        Integer year,
        Integer mileageKm,
        UserSummary owner,
        CarEngineSummary carModelEngine,
        UserCarModelSummary carModelSummary
) { }
