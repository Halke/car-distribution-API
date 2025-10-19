package com.halks.distribution_erp.Interface;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface CrudController<RequestT, ResponseT, ID> {

    @GetMapping
    ResponseEntity<List<ResponseT>> getAll();

    @GetMapping("/{id}")
    ResponseEntity<ResponseT> getOne(@PathVariable ID id);

    @PostMapping
    ResponseEntity<ResponseT> save(@RequestBody RequestT model);

    @PutMapping("/{id}")
    ResponseEntity<ResponseT> update(@PathVariable ID id, @RequestBody RequestT model);

    @DeleteMapping("/{id}")
    ResponseEntity<?> delete(@PathVariable ID id);

}
