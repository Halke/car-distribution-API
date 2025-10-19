package com.halks.distribution_erp.CarModel;

import com.halks.distribution_erp.CarBrand.CarBrand;
import com.halks.distribution_erp.CarEngine.CarEngine;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Entity(name = "car_models")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    // each model belongs to a single brand
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "brand_id", nullable = false)
    private CarBrand brand;

    @ManyToMany
    @JoinTable(
            name = "car_model_engines",
            joinColumns = @JoinColumn(name = "car_model_id"),
            inverseJoinColumns = @JoinColumn(name = "car_engine_id")
    )
    private List<CarEngine> engines = new ArrayList<>();
}
