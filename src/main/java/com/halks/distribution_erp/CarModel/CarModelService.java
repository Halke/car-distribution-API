package com.halks.distribution_erp.CarModel;

import com.halks.distribution_erp.CarBrand.CarBrand;
import com.halks.distribution_erp.CarBrand.CarBrandRepo;
import com.halks.distribution_erp.CarEngine.CarEngineRepo;
import com.halks.distribution_erp.CarModel.dto.CarModelRequest;
import com.halks.distribution_erp.CarModel.dto.CarModelResponse;
import com.halks.distribution_erp.Exception.ErrorCode;
import com.halks.distribution_erp.Exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarModelService {

    private final CarModelRepo carModelRepo;
    private final CarBrandRepo carBrandRepo;
    private final CarModelMapper carModelMapper;

    public CarModelService(CarModelRepo carModelRepo,
                           CarBrandRepo carBrandRepo,
                           CarModelMapper carModelMapper) {
        this.carModelRepo = carModelRepo;
        this.carBrandRepo = carBrandRepo;
        this.carModelMapper = carModelMapper;
    }

    public List<CarModelResponse> findAll() {
        return carModelRepo.findAll().stream()
                .map(carModelMapper::toResponse)
                .toList();
    }

    public CarModelResponse findById(Long id) {
        CarModel carModel = carModelRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        ErrorCode.CAR_MODEL_NOT_FOUND, String.format("Car model with id %d cannot be found", id))
                );

        return carModelMapper.toResponse(carModel);
    }

    public CarModelResponse create(CarModelRequest carModelRequest) {
        CarBrand brand = carBrandRepo.findById(carModelRequest.brandId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        ErrorCode.CAR_BRAND_NOT_FOUND, String.format("Car brand with id %d cannot be found", carModelRequest.brandId()))
                );

        CarModel newCarModel = carModelMapper.toEntity(carModelRequest, brand);

        return carModelMapper.toResponse(carModelRepo.save(newCarModel));
    }

    public CarModelResponse update(Long id, CarModelRequest carModelRequest) {
        CarModel updatedCarModel = carModelRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        ErrorCode.CAR_MODEL_NOT_FOUND, String.format("Car model with id %d cannot be found", id))
                );

        if (carModelRequest.name() != null) {
            updatedCarModel.setName(carModelRequest.name());
        }

        if (carModelRequest.brandId() != null) {
            CarBrand brand = carBrandRepo.findById(carModelRequest.brandId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            ErrorCode.CAR_BRAND_NOT_FOUND,
                            String.format("Car brand with id %d cannot be found", carModelRequest.brandId()))
                    );

            updatedCarModel.setBrand(brand);
        }

        return carModelMapper.toResponse(carModelRepo.save(updatedCarModel));
    }

    public void delete(Long id) {
        carModelRepo.deleteById(id);
    }
}
