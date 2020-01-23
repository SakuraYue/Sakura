package com.fh.controller;

import com.fh.common.ServerResponse;
import com.fh.model.Member;
import com.fh.service.MemberService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
public class MemberController {
 @Resource
 private MemberService memberService;

 @RequestMapping("login")
 public ServerResponse login(Member member, HttpSession session){
  ServerResponse serverResponse=  memberService.login(member);
  return serverResponse;
 }

 @RequestMapping("getCurrentLoginMember")
 public ServerResponse getCurrentLoginMember(HttpServletRequest request){
  Member loginMember = (Member) request.getAttribute("loginMember");
  Member member = memberService.getMemberById(loginMember.getId());
  return ServerResponse.success(member);
 }
}
