package com.halks.distribution_erp.Car;

import com.halks.distribution_erp.CarModel.CarModel;
import com.halks.distribution_erp.CarModel.CarModelRepo;
import com.halks.distribution_erp.Security.UserPrincipal;
import com.halks.distribution_erp.User.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarService {

    private final CarRepo carRepo;
    private final CarModelRepo carModelRepo;

    public CarService(CarRepo carRepo, CarModelRepo carModelRepo) {
        this.carRepo = carRepo;
        this.carModelRepo = carModelRepo;
    }

    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        return userPrincipal.getUser();
    }

    public List<CarResponse> getAll() {
        List<Car> allCars = carRepo.findAll();

        List<CarResponse> carResponses = new ArrayList<>();

        allCars.forEach(car -> carResponses.add(toResponse(car)));

        return carResponses;
    }

    public CarResponse getById(Long id) {
        Car carById = carRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Car not found!"));

        return toResponse(carById);
    }

    public CarResponse create(CarRequest carRequest) {
        User user = this.getCurrentUser();

        CarModel carModel = carModelRepo.findById(carRequest.getModelId())
                .orElseThrow(() -> new RuntimeException("Car Model not found!"));

        Car car = new Car();
        car.setLicensePlate(carRequest.getLicensePlate());
        car.setYear(carRequest.getYear());
        car.setColor(carRequest.getColor());
        car.setOwner(user);
        car.setModel(carModel);

        return this.toResponse(carRepo.save(car));
    }

    private CarResponse toResponse(Car car) {
        CarResponse response = new CarResponse();

        response.setId(car.getId());
        response.setLicensePlate(car.getLicensePlate());
        response.setYear(car.getYear());
        response.setColor(car.getColor());
        response.setUserId(car.getUserId());
        response.setModelId(car.getModelId());
        response.setModelName(car.getModel().getName());
        response.setBrandName(car.getModel().getBrand().getName());

        return response;
    }
}
