package com.fh.service;

import com.fh.common.ResponseEnum;
import com.fh.common.ServerResponse;
import com.fh.mapper.PaylogMapper;
import com.fh.model.Paylog;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PaylogServiceImpl implements PaylogService {
 @Resource
 private PaylogMapper paylogMapper;

 @Override
 public void addPayLog(Paylog payLog) {
  paylogMapper.addPayLog(payLog);
 }

 @Override
 public void updatePayLog(Paylog payLog) {
  paylogMapper.updatePayLog(payLog);
 }

 @Override
 public ServerResponse getPayMoney(String outTradeNo) {
  Paylog payLog = paylogMapper.getPayLogByOutTradeNo(outTradeNo);
  if(payLog == null){
   return ServerResponse.error(ResponseEnum.OUT_TRADE_NO_INVALID);
  }
  if(payLog.getPayStatus() == 1){
   return ServerResponse.error();
  }
  return ServerResponse.success(payLog.getPayMoney());
 }
}
