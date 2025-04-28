package com.campussecondhand.intercepter;

import com.campussecondhand.constant.JwtClaimsConstant;
import com.campussecondhand.context.BaseContext;
import com.campussecondhand.properties.JwtProperties;
import com.campussecondhand.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class JwtTokenUserInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtProperties jwtProperties;

    /**
     * 校验jwt
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(!(handler instanceof HandlerMethod)) {
            return true;
        }
        //从请求头获取令牌
        String token = request.getHeader(jwtProperties.getUserTokenName());
        try {
            log.info("jwt校验:{}", token);
            Claims claims = JwtUtils.parseJWT(jwtProperties.getUserSecretKey(), token);
            // 获取当前管理员id
            Long userId = Long.valueOf(claims.get(JwtClaimsConstant.USER_ID).toString());
            log.info("当前管理员id:{}", userId);
            BaseContext.setCurrentId(userId);
            return true;
        } catch (Exception ex) {
            response.setStatus(401);
            return false;
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        BaseContext.removeCurrentId();
    }
}
