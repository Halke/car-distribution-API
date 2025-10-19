package com.halks.distribution_erp.CarEngine;

import com.halks.distribution_erp.CarEngine.enums.Aspiration;
import com.halks.distribution_erp.CarEngine.enums.FuelType;
import com.halks.distribution_erp.CarEngine.enums.TransmissionType;
import com.halks.distribution_erp.CarModel.CarModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "car_engines")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarEngine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name; // e.g. "2.0 TDI", "1.6 EcoBoost"

    @Column(length = 100)
    private String manufacturer; // e.g. "Volkswagen", "BMW"

    @Enumerated(EnumType.STRING)
    @Column(name = "fuel_type", nullable = false, length = 20)
    private FuelType fuelType;

    @Column(name = "displacement_cc")
    private Integer displacementCc; // e.g. 1998

    @Column
    private Integer cylinders; // e.g. 4, 6, 8

    @Column(name = "power_hp")
    private Integer powerHp; // e.g. 150

    @Column(name = "torque_nm")
    private Integer torqueNm; // e.g. 320

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private Aspiration aspiration; // e.g. TURBOCHARGED, NATURALLY_ASPIRATED

    @Enumerated(EnumType.STRING)
    @Column(name = "transmission_type", length = 30)
    private TransmissionType transmissionType;

    @Column(name = "emission_standard", length = 20)
    private String emissionStandard; // e.g. "Euro 6"

    @Column(name = "production_start_year")
    private Integer productionStartYear;

    @Column(name = "production_end_year")
    private Integer productionEndYear;

    @ManyToMany(mappedBy = "engines")
    private List<CarModel> carModels = new ArrayList<>();
}
