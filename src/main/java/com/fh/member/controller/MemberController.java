package com.fh.member.controller;

import com.fh.common.Ignore;
import com.fh.common.ServerResponse;
import com.fh.member.model.Member;
import com.fh.member.service.MemberService;
import org.apache.http.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("member")
@CrossOrigin
@RestController
public class MemberController {
    @Autowired
    private MemberService memberService;

    @Ignore
    @RequestMapping("addMember")
    public ServerResponse addMember(Member member){
        return memberService.addMember(member);
    }
    @Ignore
    @RequestMapping("login")
    public ServerResponse login(String name,String password){
        return memberService.login(name,password);
    }

    @RequestMapping("getMember")
    public ServerResponse getMember(HttpServletRequest httpRequest){
        return memberService.getMember(httpRequest);
    }
}
