package com.example.vue.test.io.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author: Tengfei Wang
 * @description:
 * @date: Created in 3:58 下午 19/12/2019
 * @modified by:
 */
public class SocketServer {

    private static final Integer PORT = 12345;


    private static ServerSocket serverSocket;

    public synchronized static void start() throws IOException {
        if (serverSocket != null) {
            return;
        }
        try {
            serverSocket = new ServerSocket(PORT);
            System.out.println("服务启动完毕，端口：" + PORT);
            while (true) {
                Socket socket = SocketServer.serverSocket.accept();
                new Thread(new ServerHandler(socket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (serverSocket != null) {
                serverSocket.close();
                System.out.println("关闭服务");
                serverSocket = null;
            }
        }
    }

    public static void main(String[] args) {
        try {
            SocketServer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
