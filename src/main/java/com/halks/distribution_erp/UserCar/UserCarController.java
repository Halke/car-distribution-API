package com.halks.distribution_erp.UserCar;

import com.halks.distribution_erp.UserCar.dto.UserCarRequest;
import com.halks.distribution_erp.UserCar.dto.UserCarResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("${api.version.prefix}")
public class UserCarController {

    private final UserCarService userCarService;

    @Value("${api.version.prefix}")
    private String apiPrefix;

    public UserCarController(UserCarService userCarService) {
        this.userCarService = userCarService;
    }

    private URI getUserCarLocation(Long id) {
        return ServletUriComponentsBuilder.fromCurrentRequest()
                .path(String.format("%s/users/cars/{id}", apiPrefix))
                .buildAndExpand(id)
                .toUri();
    }

    @GetMapping("/users/cars")
    public ResponseEntity<List<UserCarResponse>> getAllCars() {
        return ResponseEntity.ok(userCarService.getAll());
    }

    @GetMapping("/users/cars/{carId}")
    public ResponseEntity<UserCarResponse> getCarById(@PathVariable Long carId) {
        return ResponseEntity.ok(userCarService.getById(carId));
    }

    @GetMapping("/users/{userId}/cars")
    public ResponseEntity<List<UserCarResponse>> getCarsByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(userCarService.getCarsByUserId(userId));
    }

    @PostMapping("/users/cars")
    public ResponseEntity<UserCarResponse> save(@RequestBody UserCarRequest userCarRequest) {
        UserCarResponse newUserCar = userCarService.create(userCarRequest);
        return ResponseEntity.created(this.getUserCarLocation(newUserCar.id()))
                .body(newUserCar);
    }

    @PutMapping("/users/cars/{carId}")
    public ResponseEntity<UserCarResponse> update(@PathVariable Long carId, @RequestBody UserCarRequest userCarRequest) {
        UserCarResponse updatedUserCar = userCarService.update(carId, userCarRequest);
        return ResponseEntity.created(this.getUserCarLocation(updatedUserCar.id()))
                .body(updatedUserCar);
    }

    @DeleteMapping("/users/cars/{carId}")
    public ResponseEntity<Void> delete(@PathVariable Long carId) {
        userCarService.delete(carId);
        return ResponseEntity.noContent().build();
    }
}
