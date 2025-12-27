package com.halks.distribution_erp.CarBrand;

import com.halks.distribution_erp.CarBrand.dto.CarBrandSummary;
import com.halks.distribution_erp.CarModel.dto.CarModelSummary;
import com.halks.distribution_erp.CarBrand.dto.CarBrandResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CarBrandMapper {

    public CarBrandResponse toResponse(CarBrand carBrand) {
        List<CarModelSummary> carModelSummaries = carBrand.getCarModels()
                .stream().map(model -> new CarModelSummary(
                        model.getId(),
                        model.getName()
                )).toList();

        return new CarBrandResponse(
                carBrand.getId(),
                carBrand.getName(),
                carBrand.getCountry(),
                carModelSummaries
        );
    }

    public CarBrandSummary toSummary(CarBrand carBrand) {
        return new CarBrandSummary(
                carBrand.getId(),
                carBrand.getName(),
                carBrand.getCountry()
        );
    }

}
