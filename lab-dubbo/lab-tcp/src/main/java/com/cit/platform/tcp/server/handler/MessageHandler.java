package com.cit.platform.tcp.server.handler;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Author: Richard
 * @Description: MessageHandler
 * @CreateDate: 2024/11/26 11:06
 * @UpdateUser: zhouli
 * @UpdateDate: 2024/11/26 11:06
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Slf4j
@Component
@Sharable
public class MessageHandler extends SimpleChannelInboundHandler<String> {
    @Override
    public void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        log.info("receive message: {}", msg);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        super.channelRead(ctx, msg);
        log.info("receive message: {}", msg.toString());
    }

    @Override
    public void channelActive(ChannelHandlerContext channelHandlerContext) throws Exception {
        log.info("channelActive");
    }

    @Override
    public void handlerAdded(ChannelHandlerContext channelHandlerContext) throws Exception {
        log.info("handlerAdded");
    }

    @Override
    public void channelInactive(ChannelHandlerContext channelHandlerContext) throws Exception {
        log.info("channelInactive");
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext channelHandlerContext) throws Exception {
        log.info("handlerRemoved");
    }
}
