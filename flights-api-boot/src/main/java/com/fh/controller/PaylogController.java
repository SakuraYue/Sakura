package com.fh.controller;

import com.fh.common.ServerResponse;
import com.fh.service.PaylogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaylogController {
 @Autowired
 private PaylogService payLogService;

 @RequestMapping("getPayMoney")
 public ServerResponse getPayMoney(String outTradeNo){
  try {
   ServerResponse serverResponse = payLogService.getPayMoney(outTradeNo);
   return serverResponse;
  } catch (Exception e) {
   e.printStackTrace();
   return ServerResponse.error();
  }
 }

}
