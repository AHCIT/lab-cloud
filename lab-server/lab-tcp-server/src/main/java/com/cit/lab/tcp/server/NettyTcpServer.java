package com.cit.lab.tcp.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class NettyTcpServer {

    public static void main(String[] args) {
        // 1. 创建两个 EventLoopGroup，一个用于接收连接，一个用于处理连接
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            // 2. 创建 ServerBootstrap 对象
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup)
                    // 3. 设置通道类型为 NioServerSocketChannel
                    .channel(NioServerSocketChannel.class)
                    // 4. 设置处理程序
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) {
                            ch.pipeline().addLast(new ServerHandler());
                        }
                    })
                    // 5. 配置连接参数
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            // 6. 绑定端口并启动服务器
            ChannelFuture future = bootstrap.bind(8080).sync();

            // 等待服务器关闭
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    static class ServerHandler extends io.netty.channel.SimpleChannelInboundHandler<String> {

        @Override
        protected void channelRead0(io.netty.channel.ChannelHandlerContext ctx, String msg) throws Exception {
            System.out.println("服务器收到: " + msg);

            // 向客户端发送响应
            ctx.writeAndFlush("服务器响应: 已收到你的消息\n");
        }

        @Override
        public void exceptionCaught(io.netty.channel.ChannelHandlerContext ctx, Throwable cause) {
            cause.printStackTrace();
            ctx.close();
        }
    }
}