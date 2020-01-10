package com.fh.interceptor;

import com.fh.common.AjaxException;
import com.fh.common.PreventRepeatAnnotation;
import com.fh.util.RedisUtil;
import com.fh.util.SystemConstant;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

public class IdempotentInteceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handle) throws Exception {
        //首先判断是否需要进行幂等性拦截
        //通过验证是否有自定义注解
        HandlerMethod handlerMethod = (HandlerMethod) handle;
        Method method = handlerMethod.getMethod();
        if (!method.isAnnotationPresent(PreventRepeatAnnotation.class)) {
            //不需要进行幂等性验证的接口  直接放行
            return true;
        }
        String mtoken = request.getHeader("mtoken");
        if (StringUtils.isEmpty(mtoken)) {
            throw new AjaxException(SystemConstant.MTOKEN_HIDDER_IS_NULL);
        }
        //验证mtoken 是否合法
        boolean exist = RedisUtil.exist(mtoken);
        if (!exist) {
            throw new AjaxException(SystemConstant.SUBMIT_TOKEN_DISABLED);
        }
        //mtoken只能用一次 用完就删【重点】
        Long del = RedisUtil.del(mtoken);
        if (del == 0) {
            //删除失败
            throw new AjaxException(SystemConstant.NOT_REPETE_CONFIRM);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
