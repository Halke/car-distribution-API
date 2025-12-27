package com.halks.distribution_erp.CarEngine;

import com.halks.distribution_erp.CarEngine.dto.CarEngineRequest;
import com.halks.distribution_erp.CarEngine.dto.CarEngineResponse;
import com.halks.distribution_erp.Exception.ErrorCode;
import com.halks.distribution_erp.Exception.ResourceNotFoundException;
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
                .orElseThrow(() -> new ResourceNotFoundException(
                        ErrorCode.CAR_ENGINE_NOT_FOUND, String.format("Car engine with id %d cannot be found", id))
                );

        return carEngineMapper.toResponse(carEngineById);
    }

    public CarEngineResponse create(CarEngineRequest carEngineRequest) {
        CarEngine newCarEngine = carEngineMapper.requestToEntity(carEngineRequest);
        return carEngineMapper.toResponse(carEngineRepo.save(newCarEngine));
    }

    public CarEngineResponse update(Long id, CarEngineRequest carEngineRequest) {
        CarEngine existingCarEngine = carEngineRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        ErrorCode.CAR_ENGINE_NOT_FOUND, String.format("Car engine with id %d cannot be found", id))
                );

        CarEngine updatedCarEngine = carEngineMapper.requestToEntity(carEngineRequest);

        updatedCarEngine.setId(existingCarEngine.getId());

        return carEngineMapper.toResponse(carEngineRepo.save(updatedCarEngine));
    }

    public void delete(Long id) {
        carEngineRepo.deleteById(id);
    }
}
