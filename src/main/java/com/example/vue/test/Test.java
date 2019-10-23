package com.example.vue.test;

import io.opentracing.contrib.redis.common.Action;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: Tengfei Wang
 * @description: 只有类的成员变量才会被初始化，方法的局部变量不会
 * @date: Created in 14:30 2019-06-24
 * @modified by:
 */
@Slf4j
public class Test {

    int age = 0;

    private String fileName = "/Users/tw0519/Downloads/README.md";

    /**
     * 以二进制方式读取文件，多用于读取图片，视频等
     */
    public void readFileByByte() {
        File file = new File(fileName);
        if (file.exists()) {
            long length = file.length();
            byte[] bytes = new byte[(int) length];
            FileInputStream inputStream = null;
            try {
                inputStream = new FileInputStream(file);
                inputStream.read(bytes);
                System.out.println(new String(bytes));
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (inputStream != null) {
                        inputStream.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    /**
     * 以字符方式服务文件，多用于读取文本文件
     *
     * @throws IOException
     */
    public void readFileByChar() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(this.fileName));
        String lenStr;
        while ((lenStr = br.readLine()) != null) {
            System.out.println(lenStr);
        }
        br.close();
    }

    public void lam1() {
        MathOperation c = (a, b) -> a + b + 1;
        System.out.println(c.operation(2, 4));
    }

    /**
     * lambda 接口
     */
    interface MathOperation {

        /**
         * 定义方法
         * @param a 参数1
         * @param b 参数2
         * @return 操作结果
         */
        int operation(int a, int b);
    }

    public void lam2() {
        Car car = new Car();
        car.setCarId(1);
        car.setCarName("宾利");
        ArrayList<Car> cars = new ArrayList<>();
        cars.add(car);
        List<String> collect = cars.stream().map(Car::getCarName).collect(Collectors.toList());
        System.out.println(collect);
    }




    public static void main(String[] args) throws IOException {
        Test test = new Test();
        test.lam2();

    }

}
