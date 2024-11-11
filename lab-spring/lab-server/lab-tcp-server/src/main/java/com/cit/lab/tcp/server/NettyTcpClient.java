package com.cit.lab.tcp.server;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class NettyTcpClient {

    public static void main(String[] args) {
        // 1. 创建 EventLoopGroup
        EventLoopGroup group = new NioEventLoopGroup();

        try {
            // 2. 创建 Bootstrap 对象
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    // 3. 设置通道类型为 NioSocketChannel
                    .channel(NioSocketChannel.class)
                    // 4. 设置处理程序
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) {
                            ch.pipeline().addLast(new ClientHandler());
                        }
                    })
                    // 5. 配置连接参数
                    .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                    .option(ChannelOption.SO_KEEPALIVE, true);

            // 6. 连接到服务器
            ChannelFuture future = bootstrap.connect("192.168.1.11", 8080).sync();

            // 等待连接关闭
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
    }

    static class ClientHandler extends io.netty.channel.SimpleChannelInboundHandler<String> {

        @Override
        public void channelActive(io.netty.channel.ChannelHandlerContext ctx) {
            // 连接建立后发送消息
            ctx.writeAndFlush("客户端发送: 你好，服务器\n");
        }

        @Override
        protected void channelRead0(io.netty.channel.ChannelHandlerContext ctx, String msg) throws Exception {
            System.out.println("客户端收到: " + msg);
        }

        @Override
        public void exceptionCaught(io.netty.channel.ChannelHandlerContext ctx, Throwable cause) {
            cause.printStackTrace();
            ctx.close();
        }
    }
}