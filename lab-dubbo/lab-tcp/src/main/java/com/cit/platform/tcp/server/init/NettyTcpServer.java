package com.cit.platform.tcp.server.init;

import com.cit.platform.tcp.server.config.DemoConfig;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class NettyTcpServer implements CommandLineRunner {

    private final DemoConfig demoConfig;

    @Autowired
    public NettyTcpServer(DemoConfig demoConfig) {
        this.demoConfig = demoConfig;
    }

    @Override
    public void run(String... args) {

        new Thread(() -> { // 1. 创建两个 EventLoopGroup，一个用于接收连接，一个用于处理连接
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
                                ch.pipeline().addLast(new LoggingHandler(LogLevel.INFO));
                                ch.pipeline().addLast(new IdleStateHandler(0, 0, 300));
                                ch.pipeline().addLast(new ServerHandler());
                            }
                        })
                        // 5. 配置连接参数
                        .childOption(ChannelOption.SO_KEEPALIVE, true)
                        .childOption(ChannelOption.TCP_NODELAY, true)
                        .childOption(ChannelOption.ALLOW_HALF_CLOSURE, true)
                        .childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
                        .childOption(ChannelOption.WRITE_BUFFER_WATER_MARK, new WriteBufferWaterMark(32 * 1024, 64 * 1024));

                // 6. 绑定端口并启动服务器
                ChannelFuture future = bootstrap.bind(demoConfig.getPort()).sync();
                log.info("Tcp server started on port ===> {}", demoConfig.getPort());

                // 等待服务器关闭
                future.channel().closeFuture().sync();
            } catch (InterruptedException e) {
                log.error("服务器启动失败", e);
                Thread.currentThread().interrupt();
            }
        }).start();
    }

    static class ServerHandler extends ChannelInboundHandlerAdapter {

        @Override
        public void channelRead(io.netty.channel.ChannelHandlerContext ctx, Object msg) {
            log.info("服务器收到:{} ", msg.toString());
            // 向客户端发送响应
            ctx.writeAndFlush("服务器响应: 已收到你的消息");
        }

        @Override
        public void exceptionCaught(io.netty.channel.ChannelHandlerContext ctx, Throwable cause) {
            log.error("发生异常", cause);
            ctx.close();
        }
    }
}