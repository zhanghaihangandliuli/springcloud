package com.lagou.liuyu.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author LiuYu
 * @date 2022/5/16 22:27
 */
@Slf4j
@RefreshScope
@Component
public class IpFilter implements GlobalFilter, Ordered {

    @Value("${minute:10}")
    private Integer minute;
    @Value("${number:3}")
    private Integer number;

    private final Map<String, List<LocalDateTime>> IP_MAP = new HashMap<>();

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 获取对应的客户端IP地址
        final ServerHttpRequest request = exchange.getRequest();
        final ServerHttpResponse response = exchange.getResponse();
        final String hostString = request.getRemoteAddress().getHostString();

        final String path = request.getURI().getPath();
        if(path.contains("register")){
            final List<LocalDateTime> dates = IP_MAP.get(hostString);
            // 如果当前ip第一次请求，则进行记录然后放行
            if(CollectionUtils.isEmpty(dates)){
                log.error("当前ip:【{}】第一次请求注册接口允许放行.", hostString);
                List<LocalDateTime> nowDates = new ArrayList<>();
                nowDates.add(LocalDateTime.now());
                IP_MAP.put(hostString, nowDates);
                return chain.filter(exchange);
            }
            // 如果这里注册的次数小于配置的次数，则直接放行
            if(dates.size() < number){
                log.error("当前ip:【{}】，请求第【{}】次注册接口，小于限制【{}】次，允许放行.", hostString, dates.size(), number);
                dates.add(LocalDateTime.now());
                IP_MAP.put(hostString, dates);
                return chain.filter(exchange);
            }

            final LocalDateTime localDateTime = dates.get(number - 1);
            if(LocalDateTime.now().minusMinutes(minute).isBefore(localDateTime)){
                // 如果当前ip在minute分钟内注册超过number次，则不允许当前注册请求
                response.setStatusCode(HttpStatus.SEE_OTHER);
                log.error("当前ip:【{}】在【{}】分钟内注册超过【{}】次，不允许放行.", hostString, minute, number);
                String data = "您频繁进行注册，请求已被拒绝";
                final DataBuffer wrap = response.bufferFactory().wrap(data.getBytes());

                return response.writeWith(Mono.just(wrap));
            }
            dates.add(LocalDateTime.now());
        }

        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }

}
