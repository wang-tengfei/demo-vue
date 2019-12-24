package com.example.vue.test.io.bio2;

import com.example.vue.test.io.bio.ServerHandler;
import com.example.vue.test.io.bio.SocketServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author: Tengfei Wang
 * @description:
 * @date: Created in 3:36 下午 23/12/2019
 * @modified by:
 */
public class SocketServer2 {

    /**
     * 默认的端口号
     */
    private static int DEFAULT_PORT = 12345;

    /**
     * 单例的ServerSocket
     */
    private static ServerSocket server;

    private static ExecutorService executorService = new ThreadPoolExecutor(10, 10, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());

    public synchronized static void start(Integer port) throws IOException {
        if (server != null) {
            return;
        }
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            while (true) {
                Socket socket = serverSocket.accept();
                executorService.execute(new ServerHandler(socket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (server != null) {
                System.out.println("服务器已关闭。");
                server.close();
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
