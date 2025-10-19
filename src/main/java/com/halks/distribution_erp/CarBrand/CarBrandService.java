package com.halks.distribution_erp.CarBrand;

import com.halks.distribution_erp.CarBrand.dto.CarBrandResponse;
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
                .orElseThrow(() -> new RuntimeException("Brand not found"));

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
                .orElseThrow(() -> new RuntimeException("Brand not found"));

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
