package com.cwc.javastudenthealth.handler;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.interfaces.Claim;
import com.cwc.javastudenthealth.util.JwtUtils;
import com.cwc.javastudenthealth.util.Result;
import com.cwc.javastudenthealth.util.ResultCode;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Component
public class LogHandlerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Result result = Result.base(ResultCode.TOKEN_ERROR.getCode(), ResultCode.TOKEN_ERROR.getMsg());
        String r = JSON.toJSONString(result);
        response.setContentType("application/json; charset=utf-8");
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Authorization authorization = handlerMethod.getMethodAnnotation(Authorization.class);
            // utf-8 防止乱码

            if (authorization == null) {
                return true;
            }
            try {
                String token = request.getHeader("Authorization");

                if (ObjectUtils.isEmpty(token)) {
                    response.getWriter().write(JSON.toJSONString(Result.base(ResultCode.TOKEN_NOT_FOUND.getCode(), ResultCode.TOKEN_NOT_FOUND.getMsg())));
                    return false;
                }

                Map<String, Claim> authorizationData = JwtUtils.verifyToken(token);

                if (authorizationData
                        .get("exp")
                        .asDate()
                        .getTime() > System.currentTimeMillis() && authorizationData.get("userName") != null) {
                    return true;
                }
                response.getWriter().write(r);
                return false;
            } catch (Exception e) {
                response.getWriter().write(r);
                return false;
            }
        }
        response.getWriter().write(r);
        return true;
    }
}
