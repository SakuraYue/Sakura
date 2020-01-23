package com.fh.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.fh.common.ResponseEnum;
import com.fh.common.ServerResponse;
import com.fh.model.Member;
import com.fh.util.JsonUtil;
import com.fh.util.JwtUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

@Component
public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //处理跨域问题
        response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "http://localhost:8080");
        //处理客户端传过来的自定义头信息
        response.addHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, "content-type,abc,x-auth");
        // 处理客户端发过来put,delete
        response.addHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, "PUT,POST,DELETE,GET,OPTIONS");

        //获取请求方式
        String methodActoin = request.getMethod();
        if(methodActoin.equalsIgnoreCase("options")){
            return false;
        }

        /*//判断是否有自定义注解
        HandlerMethod handlerMethod =   (HandlerMethod)handler;
        Method method = handlerMethod.getMethod();
        if(method.isAnnotationPresent(Ignore.class)){
            //有自定义注解  不需要拦截  直接执行controller中的方法
            return  true;
        }*/


        //获取请求头信息中的token
        String token = request.getHeader("abc");
        if(StringUtils.isBlank(token)){
            JsonUtil.outJson(response, ServerResponse.error(ResponseEnum.TOKEN_IS_NULL));
            return false;
        }

        //将token字符串以.进行分割
        String[] tokenArr = token.split("\\.");

        //判断token信息是否完整
        if(tokenArr.length != 3){
            JsonUtil.outJson(response, ServerResponse.error(ResponseEnum.TOKEN_IS_SHORT_OF));
            return false;
        }



        String loginMemberJson = null;
        try {
            loginMemberJson = JwtUtil.verifyToken(token);
        } catch (Exception e) {
            JsonUtil.outJson(response, ServerResponse.error(ResponseEnum.TOKEN_VERIFY_ERROR));
            return false;
        }
        Member loginMember = JSONObject.parseObject(loginMemberJson, Member.class);

        //从redis中查看是否存在当前登录会员对应的key
        if(!redisTemplate.hasKey("member:"+loginMember.getUuid())){
            JsonUtil.outJson(response, ServerResponse.error(ResponseEnum.TOKEN_IS_EXPIRED));
            return false;
        }

        //给redis中当前登录会员对应的key进行续命操作
        redisTemplate.expire("member:"+loginMember.getUuid(),30, TimeUnit.MINUTES);

        request.setAttribute("loginMember",loginMember);
        return true;
    }
}
