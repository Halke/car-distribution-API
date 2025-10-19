package com.halks.distribution_erp.CarModel;

import com.halks.distribution_erp.CarBrand.CarBrand;
import com.halks.distribution_erp.CarBrand.CarBrandRepo;
import com.halks.distribution_erp.CarEngine.CarEngine;
import com.halks.distribution_erp.CarEngine.CarEngineRepo;
import com.halks.distribution_erp.CarModel.dto.CarModelRequest;
import com.halks.distribution_erp.CarModel.dto.CarModelResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarModelService {

    private final CarModelRepo carModelRepo;
    private final CarBrandRepo carBrandRepo;
    private final CarEngineRepo carEngineRepo;
    private final CarModelMapper carModelMapper;

    public CarModelService(CarModelRepo carModelRepo,
                           CarBrandRepo carBrandRepo,
                           CarModelMapper carModelMapper,
                           CarEngineRepo carEngineRepo) {
        this.carModelRepo = carModelRepo;
        this.carBrandRepo = carBrandRepo;
        this.carEngineRepo = carEngineRepo;
        this.carModelMapper = carModelMapper;
    }

    public List<CarModelResponse> findAll() {
        return carModelRepo.findAll().stream()
                .map(carModelMapper::toResponse)
                .toList();
    }

    public CarModelResponse findById(Long id) {
        CarModel carModel = carModelRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("CarModel not found!"));

        return carModelMapper.toResponse(carModel);
    }

    public CarModelResponse create(CarModelRequest carModelRequest) {
        CarBrand brand = carBrandRepo.findById(carModelRequest.brandId())
                .orElseThrow(() -> new RuntimeException("CarBrand not found!"));

        CarModel newCarModel = carModelMapper.toEntity(carModelRequest, brand);

        return carModelMapper.toResponse(carModelRepo.save(newCarModel));
    }

    public CarModelResponse update(Long id, CarModelRequest carModel) {
        CarModel updatedCarModel = carModelRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("CarModel not found!"));

        if (carModel.name() != null) {
            updatedCarModel.setName(carModel.name());
        }

        if (carModel.brandId() != null) {
            CarBrand brand = carBrandRepo.findById(carModel.brandId())
                    .orElseThrow(() -> new RuntimeException("CarBrand not found!"));

            updatedCarModel.setBrand(brand);
        }

        return carModelMapper.toResponse(carModelRepo.save(updatedCarModel));
    }

    public CarModelResponse addEngineToModel(Long modelId, Long engineId) {
        CarModel modelById =  carModelRepo.findById(modelId)
                .orElseThrow(() -> new RuntimeException("CarModel not found!"));
        CarEngine engineById = carEngineRepo.findById(engineId)
                .orElseThrow(() -> new RuntimeException("CarEngine not found!"));

        modelById.getEngines().add(engineById);

        return carModelMapper.toResponse(carModelRepo.save(modelById));
    }

    public void deleteEngineFromModel(Long modelId, Long engineId) {
        CarModel modelById =  carModelRepo.findById(modelId)
                .orElseThrow(() -> new RuntimeException("CarModel not found!"));
        CarEngine engineById = carEngineRepo.findById(engineId)
                .orElseThrow(() -> new RuntimeException("CarEngine not found!"));

        modelById.getEngines().remove(engineById);
    }

    public void delete(Long id) {
        carModelRepo.deleteById(id);
    }
}
