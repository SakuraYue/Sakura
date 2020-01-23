package com.fh.controller;

import com.fh.common.ServerResponse;
import com.fh.model.Member;
import com.fh.service.PayService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
public class PayController {
 @Resource
 private PayService payService;

 //创建微信支付预支付订单
 @RequestMapping("createNative")
 public ServerResponse createNative(HttpServletRequest request){
  try {
   Member loginMember = (Member) request.getAttribute("loginMember");
   ServerResponse serverResponse = payService.createNative(loginMember.getId());
   return serverResponse;
  } catch (Exception e) {
   e.printStackTrace();
   return ServerResponse.error();
  }
 }

 //查询微信支付预支付订单状态
 @RequestMapping("queryPayStatus")
 public ServerResponse queryPayStatus(HttpServletRequest request){
  try {
   Member loginMember = (Member) request.getAttribute("loginMember");
   ServerResponse serverResponse = payService.queryPayStatus(loginMember.getId());
   return serverResponse;
  } catch (Exception e) {
   e.printStackTrace();
   return ServerResponse.error();
  }
 }
}
