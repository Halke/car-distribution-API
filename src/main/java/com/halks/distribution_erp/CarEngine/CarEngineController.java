package com.halks.distribution_erp.CarEngine;

import com.halks.distribution_erp.CarEngine.dto.CarEngineRequest;
import com.halks.distribution_erp.CarEngine.dto.CarEngineResponse;
import com.halks.distribution_erp.Interface.CrudController;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("${api.version.prefix}/engines")
public class CarEngineController implements CrudController<CarEngineRequest, CarEngineResponse, Long> {

    private final CarEngineService carEngineService;

    @Value("${api.version.prefix}")
    private String apiPrefix;

    public CarEngineController(CarEngineService carEngineService) {
        this.carEngineService = carEngineService;
    }

    private URI getCarEnginelLocation(Long id) {
        return ServletUriComponentsBuilder.fromCurrentRequest()
                .path(String.format("%s/engines/{id}", apiPrefix))
                .buildAndExpand(id)
                .toUri();
    }

    @Override
    public ResponseEntity<List<CarEngineResponse>> getAll() {
        return ResponseEntity.ok(carEngineService.findAll());
    }

    @Override
    public ResponseEntity<CarEngineResponse> getOne(Long id) {
        return ResponseEntity.ok(carEngineService.findById(id));
    }

    @Override
    public ResponseEntity<CarEngineResponse> save(@RequestBody CarEngineRequest carEngineRequest) {
        CarEngineResponse carEngineResponse = carEngineService.create(carEngineRequest);
        return ResponseEntity.created(getCarEnginelLocation(carEngineResponse.id()))
                .body(carEngineResponse);
    }

    @Override
    public ResponseEntity<CarEngineResponse> update(@PathVariable Long id, @RequestBody CarEngineRequest carEngineRequest) {
        CarEngineResponse carEngineResponse = carEngineService.update(id, carEngineRequest);
        return ResponseEntity.created(getCarEnginelLocation(carEngineResponse.id()))
                .body(carEngineResponse);
    }

    @Override
    public ResponseEntity<Void> delete(Long id) {
        carEngineService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
