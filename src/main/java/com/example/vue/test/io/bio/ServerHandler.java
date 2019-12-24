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
 * @date: Created in 4:13 下午 19/12/2019
 * @modified by:
 */
public class ServerHandler implements Runnable {

    public Socket socket;

    public ServerHandler(Socket socket) {
        this.socket = socket;
    }

    @SneakyThrows
    @Override
    public void run() {
        BufferedReader reader = null;
        PrintWriter writer = null;
        try {
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream(), true);
            String expression;
            String result = "我收到你的消息，";
            while (true) {
                if ((expression = reader.readLine()) == null) {
                    break;
                }
                System.out.println("服务器收到消息：" + expression);
                result += expression;
                writer.println(result);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                reader.close();
            }
            if (writer != null) {
                writer.close();
            }
            if (socket != null) {
                socket.close();
            }
        }
    }
}
