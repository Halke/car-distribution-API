package com.halks.distribution_erp.CarModel;

import com.halks.distribution_erp.CarBrand.CarBrand;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}
