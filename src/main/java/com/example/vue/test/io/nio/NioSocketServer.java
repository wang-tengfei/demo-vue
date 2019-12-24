package com.example.vue.test.io.nio;



/**
 * @author: Tengfei Wang
 * @description:
 * @date: Created in 4:22 下午 23/12/2019
 * @modified by:
 */
public class NioSocketServer {
    private static int default_port = 12345;

    private static ServerHandle serverHandle;

    public static synchronized void start(Integer port) {
        if (serverHandle != null) {
            return;
        }
        new ServerHandle(port);
        new Thread(serverHandle, "server").start();
    }
}
