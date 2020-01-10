package com.fh.member.service;

import com.fh.common.ServerResponse;
import com.fh.member.model.Member;

import javax.servlet.http.HttpServletRequest;

public interface MemberService {
    ServerResponse addMember(Member member);

    ServerResponse login(String name,String password);

    ServerResponse getMember(HttpServletRequest httpRequest);
}
