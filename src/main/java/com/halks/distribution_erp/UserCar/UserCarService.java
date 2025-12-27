package com.halks.distribution_erp.UserCar;

import com.halks.distribution_erp.CarEngine.CarEngine;
import com.halks.distribution_erp.CarEngine.CarEngineRepo;
import com.halks.distribution_erp.CarModel.CarModel;
import com.halks.distribution_erp.CarModel.CarModelRepo;
import com.halks.distribution_erp.Exception.ErrorCode;
import com.halks.distribution_erp.Exception.ResourceNotFoundException;
import com.halks.distribution_erp.Security.UserPrincipal;
import com.halks.distribution_erp.User.User;
import com.halks.distribution_erp.UserCar.dto.UserCarRequest;
import com.halks.distribution_erp.UserCar.dto.UserCarResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserCarService {

    private final UserCarRepo userCarRepo;
    private final CarModelRepo carModelRepo;
    private final CarEngineRepo carEngineRepo;
    private final UserCarMapper userCarMapper;

    public UserCarService(UserCarRepo userCarRepo,
                          CarModelRepo carModelRepo,
                          CarEngineRepo carEngineRepo,
                          UserCarMapper userCarMapper) {
        this.userCarRepo = userCarRepo;
        this.carModelRepo = carModelRepo;
        this.carEngineRepo = carEngineRepo;
        this.userCarMapper = userCarMapper;
    }

    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        return userPrincipal.getUser();
    }

    public List<UserCarResponse> getAll() {
        return userCarRepo.findAll()
                .stream().map(userCarMapper::toResponse).toList();
    }

    public List<UserCarResponse> getCarsByUserId(Long userId) {
        return userCarRepo.findByOwner_Id(userId)
                .stream().map(userCarMapper::toResponse).toList();
    }

    public UserCarResponse getById(Long id) {
        UserCar car = userCarRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        ErrorCode.CAR_NOT_FOUND, String.format("Car with id %d cannot be found", id))
                );

        return userCarMapper.toResponse(car);
    }

    public UserCarResponse create(UserCarRequest userCarRequest) {
        User user = this.getCurrentUser();

        CarModel carModel = carModelRepo.findById(userCarRequest.carModelId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        ErrorCode.CAR_MODEL_NOT_FOUND, String.format("Car model with id %d cannot be found",
                        userCarRequest.carModelId()))
                );

        CarEngine carEngine = carEngineRepo.findById(userCarRequest.carEngineId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        ErrorCode.CAR_ENGINE_NOT_FOUND, String.format("Car engine with id %d cannot be found",
                        userCarRequest.carEngineId()))
                );

        UserCar car = new UserCar();

        car.setYear(userCarRequest.year());
        car.setColor(userCarRequest.color());
        car.setMileageKm(userCarRequest.mileageKm());
        car.setVin(userCarRequest.vin());
        car.setOwner(user);
        car.setModel(carModel);
        car.setEngine(carEngine);

        return userCarMapper.toResponse(userCarRepo.save(car));
    }

    public UserCarResponse update(Long carId, UserCarRequest userCarRequest) {
        UserCar updatedCar = userCarRepo.findById(carId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        ErrorCode.CAR_NOT_FOUND, String.format("Car with id %d cannot be found", carId))
                );

        User user = this.getCurrentUser();

        if (userCarRequest.carModelId() != null) {
            CarModel carModel = carModelRepo.findById(userCarRequest.carModelId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            ErrorCode.CAR_MODEL_NOT_FOUND, String.format("Car model with id %d cannot be found",
                            userCarRequest.carModelId()))
                    );
            updatedCar.setModel(carModel);
        }

        if (userCarRequest.carEngineId() != null) {
            CarEngine carEngine = carEngineRepo.findById(userCarRequest.carEngineId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            ErrorCode.CAR_ENGINE_NOT_FOUND, String.format("Car engine with id %d cannot be found",
                            userCarRequest.carEngineId()))
                    );
            updatedCar.setEngine(carEngine);
        }

        updatedCar.setYear(userCarRequest.year());
        updatedCar.setColor(userCarRequest.color());
        updatedCar.setMileageKm(userCarRequest.mileageKm());
        updatedCar.setVin(userCarRequest.vin());
        updatedCar.setOwner(user);

        return userCarMapper.toResponse(userCarRepo.save(updatedCar));
    }

    public void delete(Long id) {
        userCarRepo.deleteById(id);
    }
}
