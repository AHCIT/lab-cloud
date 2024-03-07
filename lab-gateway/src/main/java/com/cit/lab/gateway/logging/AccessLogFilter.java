package com.cit.lab.gateway.logging;

import com.cit.lab.gateway.util.WebFrameworkUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


/**
 * 网关的访问日志过滤器
 * <p>
 * 从功能上，它类似 yudao-spring-boot-starter-web 的 ApiAccessLogFilter 过滤器
 * <p>
 * TODO 芋艿：如果网关执行异常，不会记录访问日志，后续研究下 https://github.com/Silvmike/webflux-demo/blob/master/tests/src/test/java/ru/hardcoders/demo/webflux/web_handler/filters/logging
 *
 * @author 芋道源码
 */
@Slf4j
@Component
public class AccessLogFilter implements GlobalFilter, Ordered {


    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 将 Request 中可以直接获取到的参数，设置到网关日志
        ServerHttpRequest request = exchange.getRequest();
        // TODO traceId不打印
        log.info("route: {}, schema: {}, request method: {}, request url: {}, query param: {}, header: {}, user ip: {}",
                WebFrameworkUtils.getGatewayRoute(exchange), request.getURI().getScheme(), request.getURI().getScheme(),
                request.getURI().getRawPath(), request.getQueryParams(), request.getHeaders(),
                WebFrameworkUtils.getClientIP(exchange));
        // 继续执行过滤器链
        return chain.filter(exchange);
    }


}
