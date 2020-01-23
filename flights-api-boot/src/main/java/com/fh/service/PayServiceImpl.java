package com.fh.service;

import com.fh.common.ResponseEnum;
import com.fh.common.ServerResponse;
import com.fh.mapper.OrderMapper;
import com.fh.mapper.PaylogMapper;
import com.fh.model.FlightOrder;
import com.fh.model.Paylog;
import com.fh.sdk.MyWxConfig;
import com.fh.sdk.WXPay;
import com.fh.util.DateUtil;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class PayServiceImpl implements PayService {
 @Resource
 private RedisTemplate redisTemplate;
 @Resource
 private PaylogMapper paylogMapper;
 @Resource
 private OrderMapper orderMapper;
 @Override
 public ServerResponse createNative(Integer id) {
  //先判断当前登录会员是否有支付日志
  if(!redisTemplate.hasKey("payLog:" + id)){
   return ServerResponse.error(ResponseEnum.PAY_LOG_IS_NULL);
  }

  //从redis中取出支付日志
  Paylog payLog = (Paylog) redisTemplate.opsForValue().get("payLog:" + id);

  //调用微信支付统一下单API接口生成预支付交易单
  try {

   //创建一个微信支付对象
   WXPay wxPay = new WXPay(new MyWxConfig());

   //准备调用统一下单API接口需要的参数
   Map<String,String> paramMap = new HashMap<>();
   paramMap.put("body","飞狐商城支付订单");
   paramMap.put("out_trade_no",payLog.getOutTradeNo());
   paramMap.put("total_fee","1");
   paramMap.put("spbill_create_ip","127.0.0.1");
   //将预支付订单是失效时间设为在系统当前时间上加5分钟
//            paramMap.put("time_expire",sdf.format(new Date().getTime() + 5 * 60 * 1000));
   paramMap.put("time_expire", DateUtil.format( DateUtils.addMinutes(new Date(),2)));
   paramMap.put("notify_url", "http://www.baidu.com");
   paramMap.put("trade_type", "NATIVE");

   //调用微信支付统一下单API接口
   Map<String, String> resultMap = wxPay.unifiedOrder(paramMap);

   //判断接口返回状态码是否为SUCCESS
   if(!resultMap.get("return_code").equalsIgnoreCase("SUCCESS")){
    return ServerResponse.error(123456,"微信支付失败，错误信息为:" + resultMap.get("return_msg"));
   }

   //判断接口返回业务结果是否为SUCCESS
   if(!resultMap.get("result_code").equalsIgnoreCase("SUCCESS")){
    return ServerResponse.error(123456,"微信支付失败，错误信息为:" + resultMap.get("err_code_des"));
   }

   //获取接口返回的二维码链接
   String codeUrl = resultMap.get("code_url");

   //将订单的总金额，商家支付订单号，二维码链接响应给客户端
   Map<String,Object> responseMap = new HashMap<>();
   responseMap.put("totalPrice",payLog.getPayMoney());
   responseMap.put("outTradeNo",payLog.getOutTradeNo());
   responseMap.put("codeUrl",codeUrl);

   return ServerResponse.success(responseMap);

  } catch (Exception e) {
   e.printStackTrace();
   return ServerResponse.error();
  }
 }

 @Override
 public ServerResponse queryPayStatus(Integer id) {
  //先判断当前登录会员是否有支付日志
  if(!redisTemplate.hasKey("payLog:" + id)){
   return ServerResponse.error(ResponseEnum.PAY_LOG_IS_NULL);
  }

  //从redis中取出支付日志
  Paylog payLog = (Paylog) redisTemplate.opsForValue().get("payLog:" + id);

  Map<String,String> paramMap = new HashMap<>();
  paramMap.put("outTradeNo",payLog.getOutTradeNo());
  try {
   WXPay wxPay = new WXPay(new MyWxConfig());
   //设置一个最大的查询次数
   int count = 0;
   while(true){
    Map<String, String> resultMap = wxPay.orderQuery(paramMap);
    if(!resultMap.get("return_code").equalsIgnoreCase("success")){
     return ServerResponse.error(11111,"获取支付状态失败，错误信息为："+resultMap.get("return_msg"));
    }
    if(!resultMap.get("result_code").equalsIgnoreCase("success")){
     return ServerResponse.error(11111,"获取支付状态失败，错误信息为："+resultMap.get("err_code_des"));
    }
    if(resultMap.get("trade_state").equalsIgnoreCase("success")){

     //更新订单的支付时间和支付状态
     FlightOrder order = new FlightOrder();
     order.setId(payLog.getOrderId());
     order.setPaytime(new Date());
     order.setStatus(2); //已支付
     orderMapper.updateOrder(order);

     //更新支付日志的支付时间和支付状态
     payLog.setPayStatus(2);//已支付
     payLog.setTransactionId(resultMap.get("transactionId"));
     paylogMapper.updatePayLog(payLog);

     //清空用户支付日志
     redisTemplate.delete("payLog:" + id);

     return ServerResponse.success(payLog.getOutTradeNo());
    }
    count ++;
    if(count >= 41){
     return ServerResponse.error(22222,"查询订单支付状态超时！");
    }
    Thread.sleep(3000);
   }
  } catch (Exception e) {
   e.printStackTrace();
  }
  return ServerResponse.error();
 }

 public static void main(String[] args) {
  SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
  System.out.println(sdf.format(new Date().getTime() + 5 * 60 * 1000));
 }
}
