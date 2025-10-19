package com.halks.distribution_erp.CarBrand;

import com.halks.distribution_erp.CarBrand.dto.CarBrandResponse;
import com.halks.distribution_erp.Interface.CrudController;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("${api.version.prefix}/brands")
public class CarBrandController implements CrudController<CarBrand, CarBrandResponse, Long> {

    private final CarBrandService carBrandService;

    @Value("${api.version.prefix}")
    private String apiPrefix;

    public CarBrandController(CarBrandService carBrandService) {
        this.carBrandService = carBrandService;
    }

    private URI getBrandLocation(Long id) {
        return ServletUriComponentsBuilder.fromCurrentRequest()
                .path(String.format("%s/brands/{id}", apiPrefix))
                .buildAndExpand(id)
                .toUri();
    }

    @Override
    public ResponseEntity<List<CarBrandResponse>> getAll() {
        return ResponseEntity.ok(carBrandService.findAll());
    }

    @Override
    public ResponseEntity<CarBrandResponse> getOne(@PathVariable Long id) {
        return ResponseEntity.ok(carBrandService.findBrandById(id));
    }

    @Override
    public ResponseEntity<CarBrandResponse> save(@RequestBody CarBrand carBrand) {
        CarBrandResponse newBrand = carBrandService.create(carBrand);
        return ResponseEntity.created(getBrandLocation(newBrand.id())).body(newBrand);
    }

    @Override
    public ResponseEntity<CarBrandResponse> update(@PathVariable Long id, @RequestBody CarBrand carBrand) {
        CarBrandResponse updatedCarBrand = carBrandService.update(id, carBrand);
        return ResponseEntity.created(getBrandLocation(updatedCarBrand.id())).body(updatedCarBrand);
    }

    @Override
    public ResponseEntity<?> delete(@PathVariable Long id) {
        carBrandService.delete(id);
        return ResponseEntity.ok().build();
    }
}
