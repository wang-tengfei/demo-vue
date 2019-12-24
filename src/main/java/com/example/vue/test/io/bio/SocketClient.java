package com.example.vue.test.io.bio;

import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author: Tengfei Wang
 * @description:
 * @date: Created in 4:31 下午 19/12/2019
 * @modified by:
 */
public class SocketClient {

    private static final Integer PORT = 12345;

    private static final String SERVER_IP = "127.0.0.1";

    @SneakyThrows
    public static void send(String msg) {
        System.out.println("send msg :" + msg);
        Socket socket = null;
        BufferedReader reader = null;
        PrintWriter writer = null;
        try {
            socket = new Socket(SERVER_IP, PORT);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream(), true);
            writer.println(msg);
            System.out.println("result is：" + reader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                writer.close();
            }
            if (reader != null) {
                reader.close();
            }
            if (socket != null) {
                socket.close();
            }
        }
    }

    public static void main(String[] args) {
        new Thread(() -> {
            SocketClient.send("test");
        }).start();

    }
}
