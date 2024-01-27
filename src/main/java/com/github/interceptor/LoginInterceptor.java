package com.github.interceptor;

import com.github.utils.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

/**
 * @ClassName LoginInterceptor
 * @Description 认证授权拦截器
 * @Author qyh
 * @Date 2024/1/27 14:40
 * @Version 1.0
 **/
@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String authorization = request.getHeader("Authorization");
        try {
            JwtUtil.parseToken(authorization);
            return true;
        }catch (Exception e){
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return false;
        }
    }
}
