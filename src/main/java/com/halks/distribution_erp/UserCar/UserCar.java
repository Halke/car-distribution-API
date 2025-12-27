package com.halks.distribution_erp.UserCar;

import com.halks.distribution_erp.CarEngine.CarEngine;
import com.halks.distribution_erp.CarModel.CarModel;
import com.halks.distribution_erp.User.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "user_cars")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String color;
    private String vin;
    private Integer year;
    private Integer mileageKm;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User owner;

    @ManyToOne
    @JoinColumn(name = "car_model_id", nullable = false)
    private CarModel model;

    @ManyToOne
    @JoinColumn(name = "car_engine_id", nullable = false)
    private CarEngine engine;
}
