package com.halks.distribution_erp.CarEngine;

import com.halks.distribution_erp.CarEngine.dto.CarEngineRequest;
import com.halks.distribution_erp.CarEngine.dto.CarEngineResponse;
import com.halks.distribution_erp.CarEngine.dto.CarEngineSummary;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class CarEngineMapper {

    public CarEngineResponse toResponse(CarEngine carEngine){
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
                carEngine.getProductionEndYear()
        );
    }

    public CarEngine requestToEntity(CarEngineRequest carEngineRequest){
        CarEngine newCarEngine = new CarEngine();

        newCarEngine.setName(carEngineRequest.name());
        newCarEngine.setManufacturer(carEngineRequest.manufacturer());
        newCarEngine.setFuelType(carEngineRequest.fuelType());
        newCarEngine.setDisplacementCc(carEngineRequest.displacementCc());
        newCarEngine.setCylinders(carEngineRequest.cylinders());
        newCarEngine.setPowerHp(carEngineRequest.powerHp());
        newCarEngine.setTorqueNm(carEngineRequest.torqueNm());
        newCarEngine.setAspiration(carEngineRequest.aspiration());
        newCarEngine.setTransmissionType(carEngineRequest.transmissionType());
        newCarEngine.setEmissionStandard(carEngineRequest.emissionStandard());
        newCarEngine.setProductionStartYear(carEngineRequest.productionStartYear());
        newCarEngine.setProductionEndYear(carEngineRequest.productionEndYear());

        return newCarEngine;
    }

    public CarEngineSummary toSummary(CarEngine carEngine) {
        return new CarEngineSummary(
                carEngine.getId(),
                carEngine.getName(),
                carEngine.getManufacturer(),
                carEngine.getFuelType(),
                carEngine.getProductionStartYear(),
                carEngine.getProductionEndYear()
        );
    }
}
