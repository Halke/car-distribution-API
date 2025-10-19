package com.halks.distribution_erp.CarEngine;

import com.halks.distribution_erp.CarEngine.dto.CarEngineRequest;
import com.halks.distribution_erp.CarEngine.dto.CarEngineResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarEngineService {

    private final CarEngineRepo carEngineRepo;
    private final CarEngineMapper carEngineMapper;

    public CarEngineService(CarEngineRepo carEngineRepo, CarEngineMapper carEngineMapper) {
        this.carEngineRepo = carEngineRepo;
        this.carEngineMapper = carEngineMapper;
    }

    public List<CarEngineResponse> findAll() {
        return carEngineRepo.findAll()
                .stream().map(carEngineMapper::toResponse).toList();
    }

    public CarEngineResponse findById(Long id) {
        CarEngine carEngineById = carEngineRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("CarEngine not found!"));
        return carEngineMapper.toResponse(carEngineById);
    }

    public CarEngineResponse create(CarEngineRequest carEngineRequest) {
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

        return carEngineMapper.toResponse(carEngineRepo.save(newCarEngine));
    }

    public CarEngineResponse update(Long id, CarEngineRequest carEngineRequest) {
        CarEngine updatedCarEngine = carEngineRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("CarEngine not found!"));

        BeanUtils.co
    }
}
