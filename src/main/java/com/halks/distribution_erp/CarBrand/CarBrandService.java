package com.halks.distribution_erp.CarBrand;

import com.halks.distribution_erp.CarBrand.dto.CarBrandResponse;
import com.halks.distribution_erp.Exception.ErrorCode;
import com.halks.distribution_erp.Exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarBrandService {

    private final CarBrandRepo carBrandRepo;
    private final CarBrandMapper carBrandMapper;

    public CarBrandService(CarBrandRepo carBrandRepo, CarBrandMapper carBrandMapper) {
        this.carBrandRepo = carBrandRepo;
        this.carBrandMapper = carBrandMapper;
    }

    public CarBrandResponse findBrandById(Long id) {
        CarBrand brand = carBrandRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        ErrorCode.CAR_BRAND_NOT_FOUND, String.format("Car brand with id %d cannot be found", id))
                );

        return carBrandMapper.toResponse(brand);
    }

    public List<CarBrandResponse> findAll() {
        return carBrandRepo.findAll().stream()
                .map(carBrandMapper::toResponse)
                .toList();
    }

    public CarBrandResponse create(CarBrand carBrand) {
        return carBrandMapper.toResponse(carBrandRepo.save(carBrand));
    }

    public CarBrandResponse update(Long id, CarBrand carBrand) {

        CarBrand updatedCarBrand = carBrandRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        ErrorCode.CAR_BRAND_NOT_FOUND, String.format("Car brand with id %d cannot be found", id))
                );

        if(carBrand.getName() != null) {
            updatedCarBrand.setName(carBrand.getName());
        }

        if(carBrand.getCountry() != null) {
            updatedCarBrand.setCountry(carBrand.getCountry());
        }

        return carBrandMapper.toResponse(carBrandRepo.save(updatedCarBrand));
    }

    public void delete(Long id) {
        carBrandRepo.deleteById(id);
    }
}
