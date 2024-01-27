package com.github.interceptor;

import com.github.utils.JwtUtil;
import com.github.utils.ThreadLocalUtil;
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
            Map<String, Object> claims = JwtUtil.parseToken(authorization);
            //保存用户名到ThreadLocal
            ThreadLocalUtil.set(claims);
            return true;
        }catch (Exception e){
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return false;
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //清空ThreadLocal的数据
        ThreadLocalUtil.remove();
    }
}
