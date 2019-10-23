package com.example.vue.test;

import org.springframework.data.mongodb.core.aggregation.ArrayOperators;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.ToIntBiFunction;

/**
 * @author: Tengfei Wang
 * @description:
 * @date: Created in 17:56 2019-10-12
 * @modified by:
 */
public class Functional {

    public static void eval(List<Integer> list, Predicate<Integer> predicate) {
        for (Integer num : list) {
            if (predicate.test(num)) {
                System.out.println("success " + num);
            }
        }
    }

    public static void eval(Integer a, Integer b, ToIntBiFunction<Integer, Integer> toIntBiFunction) {
        int asInt = toIntBiFunction.applyAsInt(a, b);
        System.out.println(asInt);
    }

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);

        eval(list, n-> true);
        System.out.println("------------------");

        eval(list, n -> n % 2 == 0);
        System.out.println("------------------");

        eval(1,9, (a, b)-> a + b + 1);
    }
}
