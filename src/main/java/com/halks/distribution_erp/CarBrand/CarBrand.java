package com.halks.distribution_erp.CarBrand;

import com.halks.distribution_erp.CarModel.CarModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "car_brands")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarBrand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String country;

    @OneToMany(mappedBy = "brand", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<CarModel> carModels = new ArrayList<>();
}
