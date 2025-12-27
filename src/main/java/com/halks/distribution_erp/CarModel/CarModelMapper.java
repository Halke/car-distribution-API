package com.halks.distribution_erp.CarModel;

import com.halks.distribution_erp.CarBrand.CarBrand;
import com.halks.distribution_erp.CarModel.dto.CarModelBrandSummary;
import com.halks.distribution_erp.CarModel.dto.CarModelRequest;
import com.halks.distribution_erp.CarModel.dto.CarModelResponse;
import org.springframework.stereotype.Component;

@Component
public class CarModelMapper {

    public CarModelResponse toResponse(CarModel carModel){
        CarBrand carBrand = carModel.getBrand();

        CarModelBrandSummary carModelBrandSummary = new CarModelBrandSummary(
                carBrand.getId(),
                carBrand.getName(),
                carBrand.getCountry()
        );

        return new CarModelResponse(
                carModel.getId(),
                carModel.getName(),
                carModelBrandSummary
        );
    }

    public CarModel toEntity(CarModelRequest carModelResponse, CarBrand brand) {
        CarModel carModel = new CarModel();

        carModel.setName(carModelResponse.name());
        carModel.setBrand(brand);

        return carModel;
    }
}
