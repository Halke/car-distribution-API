package com.halks.distribution_erp.Car;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController @RequestMapping("${api.version.prefix}/cars")
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    public List<CarResponse> getAllCars() {
        return carService.getAll();
    }

    @GetMapping("/{carId}")
    public CarResponse getCarById(@PathVariable Long carId) {
        return carService.getById(carId);
    }

    @PostMapping
    public CarResponse createCar(@RequestBody CarRequest carRequest) {
        return carService.create(carRequest);
    }
}
