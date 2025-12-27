package com.halks.distribution_erp.CarEngine.dto;

import com.halks.distribution_erp.CarEngine.enums.Aspiration;
import com.halks.distribution_erp.CarEngine.enums.FuelType;
import com.halks.distribution_erp.CarEngine.enums.TransmissionType;

public record CarEngineResponse(
        Long id,
        String name,
        String manufacturer,
        FuelType fuelType,
        Integer displacementCc,
        Integer cylinders,
        Integer powerHp,
        Integer torqueNm,
        Aspiration aspiration,
        TransmissionType transmissionType,
        String emissionStandard,
        Integer productionStartYear,
        Integer productionEndYear
) { }
