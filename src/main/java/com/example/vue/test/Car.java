package com.example.vue.test;

import lombok.Data;

/**
 * @author: Tengfei Wang
 * @description:
 * @date: Created in 17:11 2019-10-12
 * @modified by:
 */
@Data
public class Car {

    private int carId;

    private String carName;

    public static String getName(Car car) {
        System.out.println(car.getCarName());
        return car.getCarName();
    }
}
