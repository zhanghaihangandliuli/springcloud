package com.lagou.liuyu.filter;

import com.lagou.liuyu.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author LiuYu
 * @date 2022/5/16 22:27
 */
@Slf4j
@Component
public class UnifiedAuthenticationFilter implements GlobalFilter, Ordered {

    @Autowired
    private IUserService userService;

    private static final List<String> RELEASE_PATH = Arrays.asList("/api/user/", "/api/code/");

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 获取对应的客户端IP地址
        final ServerHttpRequest request = exchange.getRequest();
        final ServerHttpResponse response = exchange.getResponse();
        final String hostString = request.getRemoteAddress().getHostString();

        final String path = request.getURI().getPath();
        log.info("当前请求的path为: {}", path);
        // 如果当前请求不是登录或者注册接口，则校验token是否合法
        boolean isRelease = false;
        for (String releasePath : RELEASE_PATH) {
            if(path.startsWith(releasePath)){
                isRelease = true;
                break;
            }
        }

        if(!isRelease){
            final MultiValueMap<String, HttpCookie> cookies = request.getCookies();
            final List<HttpCookie> userToken = cookies.get("token");
            if(CollectionUtils.isEmpty(userToken)){
                // 当前请求未携带token，直接拦截
                // 如果当前ip在minute分钟内注册超过number次，则不允许当前注册请求
                response.setStatusCode(HttpStatus.UNAUTHORIZED);
                log.error("当前ip:【{}】请求【{}】接口未登录，不允许放行.", hostString, path);
                String data = "当前请求未登录，请登录后重试!";
                final DataBuffer wrap = response.bufferFactory().wrap(data.getBytes());

                return response.writeWith(Mono.just(wrap));
            }

            final HttpCookie httpCookie = userToken.get(0);
            final Object email = userService.userInfo(httpCookie.getValue());
            if(email == null){
                // 当前请求未携带token，直接拦截
                // 如果当前ip在minute分钟内注册超过number次，则不允许当前注册请求
                response.setStatusCode(HttpStatus.UNAUTHORIZED);
                log.error("当前ip:【{}】请求【{}】接口未登录，不允许放行.", hostString, path);
                String data = "当前请求未登录，请登录后重试!";
                final DataBuffer wrap = response.bufferFactory().wrap(data.getBytes());

                return response.writeWith(Mono.just(wrap));
            }
        }

        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 1;
    }

}
