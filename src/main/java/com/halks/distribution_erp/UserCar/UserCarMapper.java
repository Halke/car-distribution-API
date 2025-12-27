package com.halks.distribution_erp.UserCar;

import com.halks.distribution_erp.CarBrand.CarBrandMapper;
import com.halks.distribution_erp.CarEngine.CarEngineMapper;
import com.halks.distribution_erp.CarModel.CarModel;
import com.halks.distribution_erp.User.UserMapper;
import com.halks.distribution_erp.UserCar.dto.UserCarModelSummary;
import com.halks.distribution_erp.UserCar.dto.UserCarResponse;
import org.springframework.stereotype.Component;

@Component
public class UserCarMapper {

    private final CarBrandMapper carBrandMapper;
    private final CarEngineMapper carEngineMapper;
    private final UserMapper userMapper;

    public UserCarMapper(CarBrandMapper carBrandMapper,
                         CarEngineMapper carEngineMapper,
                         UserMapper userMapper) {
        this.carBrandMapper = carBrandMapper;
        this.carEngineMapper = carEngineMapper;
        this.userMapper = userMapper;
    }

    public UserCarResponse toResponse(UserCar car) {
        return new UserCarResponse(
                car.getId(),
                car.getColor(),
                car.getVin(),
                car.getYear(),
                car.getMileageKm(),
                this.userMapper.toResponse(car.getOwner()),
                this.carEngineMapper.toSummary(car.getEngine()),
                this.carModelToUserCarModelSummary(car.getModel())
        );
    }

    public UserCarModelSummary carModelToUserCarModelSummary(CarModel carModel) {
        return new UserCarModelSummary(
                carModel.getId(),
                carModel.getName(),
                carBrandMapper.toSummary(carModel.getBrand())
        );
    }

}
