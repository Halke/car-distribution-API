package com.halks.distribution_erp.CarEngine;

import com.halks.distribution_erp.CarBrand.dto.CarBrandSummary;
import com.halks.distribution_erp.CarEngine.dto.CarEngineModelSummary;
import com.halks.distribution_erp.CarEngine.dto.CarEngineResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CarEngineMapper {

    public CarEngineResponse toResponse(CarEngine carEngine){
        List<CarEngineModelSummary> carModels = carEngine.getCarModels()
                .stream().map(model -> new CarEngineModelSummary(
                        model.getId(),
                        model.getName(),
                        new CarBrandSummary(
                                model.getBrand().getId(),
                                model.getBrand().getName(),
                                model.getBrand().getCountry()
                        )
                )).toList();

        return new CarEngineResponse(
                carEngine.getId(),
                carEngine.getName(),
                carEngine.getManufacturer(),
                carEngine.getFuelType(),
                carEngine.getDisplacementCc(),
                carEngine.getCylinders(),
                carEngine.getPowerHp(),
                carEngine.getTorqueNm(),
                carEngine.getAspiration(),
                carEngine.getTransmissionType(),
                carEngine.getEmissionStandard(),
                carEngine.getProductionStartYear(),
                carEngine.getProductionEndYear(),
                carModels
        );
    }

//    public CarEngine toEntity(CarEngineResponse carEngineResponse){
//        return null;
//    }

}
