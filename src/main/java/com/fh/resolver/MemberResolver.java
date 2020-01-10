package com.fh.resolver;

import com.fh.common.MemberAnnotation;
import com.fh.member.model.Member;
import com.fh.util.SystemConstant;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

public class MemberResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        if(methodParameter.hasParameterAnnotation(MemberAnnotation.class)){
            return true;
        }
        return false;
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        HttpServletRequest request = nativeWebRequest.getNativeRequest(HttpServletRequest.class);
        Member member = (Member) request.getSession().getAttribute(SystemConstant.LOGGIN_CURRENT_USER);


        String memberName = nativeWebRequest.getParameter("name");

        if(StringUtils.isNotBlank(memberName)){
            Member m = new Member();
            String passWord = nativeWebRequest.getParameter("password");
            String phone = nativeWebRequest.getParameter("phone");
            String smsCode = nativeWebRequest.getParameter("code");
            m.setName(memberName);
            m.setPassword(passWord);
            m.setPhone(phone);
            m.setCode(smsCode);
            return m;
        }

        return member;
    }
}
