package com.fh.controller;

import com.fh.common.ServerResponse;
import com.fh.model.Member;
import com.fh.service.OrderService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

@RestController
public class OrderController {
 @Resource
 private OrderService orderService;
@RequestMapping("toOrder")
 public ServerResponse toOrder(Integer id, HttpServletRequest request){
 Member member = (Member) request.getAttribute("loginMember");
 ServerResponse serverResponse = orderService.toOrder(member.getId(),id);
 return serverResponse;
 }

 @RequestMapping("queryTicket")
 public ServerResponse queryTicket(HttpServletRequest request){
  Member member = (Member) request.getAttribute("loginMember");
  ServerResponse serverResponse =orderService.queryTicket(member.getId());
  return serverResponse;
 }

 @RequestMapping("addOrder")
 public ServerResponse addOrder
  (HttpServletRequest request, Integer flighttype, BigDecimal price,String username,String idcart,Integer countperpose,BigDecimal subprice){
  Member member = (Member) request.getAttribute("loginMember");
  ServerResponse serverResponse =orderService.addOrder(member.getId(),flighttype,price,username,idcart,countperpose,subprice);
  return serverResponse;
 }

 @RequestMapping("getOrderByUserId")
 public ServerResponse getOrderByUserId(HttpServletRequest request){
  Member member = (Member) request.getAttribute("loginMember");
  ServerResponse serverResponse =orderService.getOrderByUserId(member.getId());
  return serverResponse;
 }










}
