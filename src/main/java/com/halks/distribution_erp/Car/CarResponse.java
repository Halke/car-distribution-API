package com.halks.distribution_erp.Car;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarResponse {
    private Long id;
    private String licensePlate;
    private Integer year;
    private String color;
    private Long userId;
    private Long modelId;
    private String brandName;
    private String modelName;
}
