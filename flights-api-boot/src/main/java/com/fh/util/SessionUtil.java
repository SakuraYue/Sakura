package com.fh.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

public class SessionUtil {


    public static String getSessionId(HttpServletRequest request, HttpServletResponse response){
        //先从cookie中获取sessionId
        String sessionId = CookieUtil.getSessionId(request);
        if(sessionId.isEmpty()){
            sessionId = UUID.randomUUID().toString();
        }
        //写入cookie
        CookieUtil.writeCookie(sessionId,response, SystemConstant.COOKIE_OUT_TIME_DEFAULT);

        return sessionId;
    }
}
