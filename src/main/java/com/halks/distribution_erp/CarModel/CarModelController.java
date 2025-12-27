package com.halks.distribution_erp.CarModel;

import com.halks.distribution_erp.CarModel.dto.CarModelRequest;
import com.halks.distribution_erp.CarModel.dto.CarModelResponse;
import com.halks.distribution_erp.Interface.CrudController;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("${api.version.prefix}/models")
public class CarModelController implements CrudController<CarModelRequest, CarModelResponse, Long> {

    private final CarModelService carModelService;

    @Value("${api.version.prefix}")
    private String apiPrefix;

    public CarModelController(CarModelService carModelService) {
        this.carModelService = carModelService;
    }

    private URI getCarModelLocation(Long id) {
        return ServletUriComponentsBuilder.fromCurrentRequest()
                .path(String.format("%s/models/{id}", apiPrefix))
                .buildAndExpand(id)
                .toUri();
    }

    @Override
    public ResponseEntity<List<CarModelResponse>> getAll() {
        return ResponseEntity.ok(carModelService.findAll());
    }

    @Override
    public ResponseEntity<CarModelResponse> getOne(@PathVariable Long id) {
        return ResponseEntity.ok(carModelService.findById(id));
    }

    @Override
    public ResponseEntity<CarModelResponse> save(@RequestBody CarModelRequest carModelRequest) {
        CarModelResponse newCarModel = carModelService.create(carModelRequest);
        return ResponseEntity.created(getCarModelLocation(newCarModel.id())).body(newCarModel);
    }

    @Override
    public ResponseEntity<CarModelResponse> update(@PathVariable Long id, @RequestBody CarModelRequest carModelRequest) {
        CarModelResponse newCarModel = carModelService.update(id, carModelRequest);
        return ResponseEntity.created(getCarModelLocation(newCarModel.id())).body(newCarModel);
    }

    @Override
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        carModelService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
