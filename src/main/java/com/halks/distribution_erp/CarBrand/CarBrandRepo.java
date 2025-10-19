package com.halks.distribution_erp.CarBrand;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CarBrandRepo extends JpaRepository<CarBrand, Long> {

    @Override
    public Optional<CarBrand> findById(Long id);

}
