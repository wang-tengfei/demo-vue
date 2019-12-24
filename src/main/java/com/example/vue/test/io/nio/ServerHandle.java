package com.example.vue.test.io.nio;

import lombok.SneakyThrows;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;


/**
 * @author: Tengfei Wang
 * @description:463034360463034360
 * @date: Created in 4:26 下午 23/12/2019
 * @modified by:
 */
public class ServerHandle implements Runnable {

    private Selector selector;

    private ServerSocketChannel serverChannel;

    /**
     * 标记服务器已开启
     */
    private volatile boolean started;

    public ServerHandle(Integer port) {
        try {
            //创建选择器
            selector = Selector.open();
            //打开监听通道
            serverChannel = ServerSocketChannel.open();
            //如果为 true，则此通道将被置于阻塞模式；如果为 false，则此通道将被置于非阻塞模式
            serverChannel.configureBlocking(false);
            //绑定端口 backlog设为1024
            serverChannel.socket().bind(new InetSocketAddress(port), 1024);
            //监听客户端连接请求
            serverChannel.register(selector, SelectionKey.OP_ACCEPT);
            started = true;
            System.out.println("服务已启动，端口：" + port);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }


    public void stop() {
        this.started = false;
    }


    @SneakyThrows
    @Override
    public void run() {
        //循环遍历selector
        while (started) {
            //无论是否有读写事件发生，selector每隔1s被唤醒一次
            selector.select(1000);
            Set<SelectionKey> keys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = keys.iterator();
            SelectionKey selectionKey = null;
            while (iterator.hasNext()) {
                selectionKey = iterator.next();
                iterator.remove();
                if (selectionKey.isValid()) {
                    //处理新接入的请求
                    if (selectionKey.isAcceptable()) {
                        ServerSocketChannel channel = (ServerSocketChannel) selectionKey.channel();
                        //通过ServerSocketChannel的accept创建SocketChannel实例,完成该操作意味着完成TCP三次握手，TCP物理链路正式建立
                        SocketChannel accept = channel.accept();
                        //设置为非阻塞的
                        accept.configureBlocking(false);
                        //注册为读
                        accept.register(selector, SelectionKey.OP_READ);
                    }
                    //读消息
                    if (selectionKey.isReadable()) {
                        SocketChannel channel = (SocketChannel) selectionKey.channel();
                        //创建ByteBuffer，并开辟一个1M的缓冲区
                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        //读取请求码流，返回读取到的字节数
                        int read = channel.read(buffer);
                        //读取到字节，对字节进行编解码
                        if (read > 0) {
                            //将缓冲区当前的limit设置为position=0，用于后续对缓冲区的读取操作
                            buffer.flip();
                            //根据缓冲区可读字节数创建字节数组
                            byte[] bytes = new byte[buffer.remaining()];
                            //将缓冲区可读字节数组复制到新建的数组中
                            buffer.get(bytes);
                            String msg = new String(bytes, StandardCharsets.UTF_8);
                            System.out.println("服务器收到消息：" + msg);
                            //处理数据
                            msg += "-------";

                            //发送应答消息
                            byte[] msgBytes = msg.getBytes();
                            //根据数组容量创建ByteBuffer
                            ByteBuffer byteBuffer = ByteBuffer.allocate(msgBytes.length);
                            //将字节数组复制到缓冲区
                            byteBuffer.put(msgBytes);
                            byteBuffer.flip();
                            //发送缓冲区的字节数组
                            channel.write(byteBuffer);

                        } else if (read < 0) {
                            selectionKey.cancel();
                            channel.close();
                        }
                    }
                }
            }
        }
    }
}
