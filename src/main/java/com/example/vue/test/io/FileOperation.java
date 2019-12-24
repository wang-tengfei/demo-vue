package com.example.vue.test.io;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @author: Tengfei Wang
 * @description:
 * @date: Created in 3:33 下午 18/12/2019
 * @modified by:
 */
public class FileOperation {

    public static void main(String[] args) {
        System.out.println(readFileNio("/Users/tw0519/Downloads/lab-tun-manage.sh"));
        writeFileNio("this is a test", "/Users/tw0519/Downloads/test.sh");
    }

    /**
     * 使用 nio 方式来读取文件
     *
     * @param filePath 文件路径
     * @return 文件内容
     */
    public static String readFileNio(String filePath) {
        byte[] bytes = new byte[0];
        try {
            bytes = Files.readAllBytes(Paths.get(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String(bytes, StandardCharsets.UTF_8);
    }

    /**
     * 使用 nio 方式来写入文件
     *
     * @param data     写入的数据
     * @param filePath 文件路径
     */
    public static void writeFileNio(String data, String filePath) {
        Path path = null;
        try {
            path = Files.write(Paths.get(filePath), data.getBytes(StandardCharsets.UTF_8), StandardOpenOption.CREATE);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(path);
    }
}
