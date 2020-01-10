package com.fh.interceptor;

import com.alibaba.fastjson.JSON;
import com.fh.common.AjaxException;
import com.fh.common.Ignore;
import com.fh.common.ServerResponse;
import com.fh.member.model.Member;
import com.fh.util.JwtUtil;
import com.fh.util.SystemConstant;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

public class LoginInterceptor implements HandlerInterceptor {
    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //处理跨域问题
        response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "http://localhost:8083");
        //处理客户端传过来的自定义头信息
        response.addHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, "x-auth,token,content-type");
        // 处理客户端发过来put,delete
        response.addHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, "PUT,POST,DELETE,GET,OPTIONS");

        String methodActoin = request.getMethod();
        if (methodActoin.equalsIgnoreCase("OPTIONS")) {
            return false;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        if(method.isAnnotationPresent(Ignore.class)){
            return true;
        }

        String token = request.getHeader("token");
        if(StringUtils.isEmpty(token)){
        throw new AjaxException(SystemConstant.TOKEN_IS_NULL);
        }

        if(!redisTemplate.hasKey("token")){
        throw new AjaxException(SystemConstant.SUBMIT_TOKEN_DISABLED);
        }

        ServerResponse serverResponse = JwtUtil.validateJWT(token);
        if (serverResponse.getStatus() != 200){
            throw new AjaxException(SystemConstant.TOKEN_CHECK_ERROR);
        }
        Claims claims = (Claims) serverResponse.getData();
        String memberString = claims.getSubject();
        Member member = JSON.parseObject(memberString, Member.class);

        request.getSession().setAttribute(SystemConstant.LOGGIN_CURRENT_USER, member);
        request.getSession().setAttribute(SystemConstant.TOKEN_KEY, token);

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
