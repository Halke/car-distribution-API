package com.halks.distribution_erp.Car;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarRequest {
    private String licensePlate;
    private Integer year;
    private String color;
    private Long modelId;
}
