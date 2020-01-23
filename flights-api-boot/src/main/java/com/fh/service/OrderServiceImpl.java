package com.fh.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fh.common.ResponseEnum;
import com.fh.common.ServerResponse;
import com.fh.mapper.*;
import com.fh.model.*;
import com.fh.util.IdUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class OrderServiceImpl implements OrderService {
 @Resource
 private FlightOrderItemMapper flightOrderItemmapper;
 @Resource
 private OrderMapper orderMapper;
@Resource
 private TicketMapper ticketMapper;
@Resource
private RedisTemplate redisTemplate;
@Resource
private FilghtMapper filghtMapper;
@Resource
private PaylogMapper paylogMapper;


 @Override
 public ServerResponse queryTicket(Integer id) {
  String memberkey="member:" + id;
  if (!redisTemplate.hasKey(memberkey)){
   return   ServerResponse.error(ResponseEnum.TICKET_IS_NULL);
  }else{
   List<Ticket> ticketList = (List<Ticket>) redisTemplate.opsForHash().values(memberkey);
   return ServerResponse.success(ticketList);
  }
 }

 @Override
 public ServerResponse getOrderByMemberId(Integer id) {
  return null;
 }

 @Override
 public ServerResponse toOrder(Integer memberid, Integer id) {
  List<Ticket> ticketList = (List<Ticket>) ticketMapper.selectTicketByFlightId(id);
  String ticketkey="ticket:" + id;
  String memberkey="member:" + memberid;
  if (!redisTemplate.hasKey(memberkey)){
   redisTemplate.opsForHash().put(memberkey,ticketkey,ticketList);
  }else {
   return   ServerResponse.error(ResponseEnum.TICKET_IS_NULL);
  }
  return ServerResponse.success();
 }

 @Override
 public ServerResponse addOrder(Integer id, Integer flighttype, BigDecimal price, String username, String idcart, Integer countperpose, BigDecimal subprice) {
  String memberkey="member:" + id;
  if (!redisTemplate.hasKey(memberkey)){
   return   ServerResponse.error(ResponseEnum.TICKET_IS_NULL);
  }else{
   List<Ticket> ticketList = (List<Ticket>) redisTemplate.opsForValue().get("ticket:1");
   for (int i=0;i<ticketList.size();i++) {
    System.out.println(ticketList.get(i).getFlightId());
    System.out.println("aaa");
    Flight flight = filghtMapper.selectById(ticketList.get(i).getFlightId());
    FlightOrder flightOrder=new FlightOrder();
    flightOrder.setFlightid(flight.getId());
    flightOrder.setCreatetime(new Date());
    String orderid=IdUtil.createId();
    flightOrder.setId(orderid);
    flightOrder.setPaytype(flighttype);
    flightOrder.setStatus(1);
    flightOrder.setTotalcount(countperpose.longValue());
    flightOrder.setTotalprice(subprice);
    flightOrder.setUserid(id);
    orderMapper.addOrder(flightOrder);
    FlightOrderItem flightOrderItem=new FlightOrderItem();
    flightOrderItem.setIdcard(idcart);
    flightOrderItem.setOrderid(orderid);
    flightOrderItem.setPrice(price);
    flightOrderItem.setRealname(username);
    QueryWrapper<Ticket> queryWrapper=new QueryWrapper();
    Ticket selectOne = ticketMapper.selectOne(queryWrapper.eq("type",flighttype ));
    flightOrderItem.setTicketid(selectOne.getId());
    flightOrderItem.setTickettype(flighttype);
    flightOrderItem.setUserid(id);
    flightOrderItemmapper.addorderItem(flightOrderItem);

    //生成支付日志
    //支付日志表:用来记录用户的支付行为，比如说支付订单号，订单号，微信支付订单号，支付了多少钱，谁支付的，什么时候支付的，支付的方式，支付的状态(1代表未支付，2代表已支付)
    Paylog payLog = new Paylog();
    payLog.setOutTradeNo(IdUtil.createId());
    payLog.setPayStatus(1);
    payLog.setMemberId(id);
    payLog.setPayType(1);
    payLog.setPayTime(new Date());
    payLog.setPayMoney(price);
    payLog.setOrderId(orderid);

    //将支付日志保存到支付日志表中
    paylogMapper.addPayLog(payLog);

    //将支付日志保存到redis中，方便在支付页面取出支付日志
    redisTemplate.opsForValue().set("payLog:" + id,payLog,30, TimeUnit.MINUTES);

    break;
   }
  }

  return ServerResponse.success();
 }

 @Override
 public ServerResponse getOrderByUserId(Integer id) {
  FlightOrderItem flightOrderItem= flightOrderItemmapper.getOrderByUserId(id);
  return ServerResponse.success(flightOrderItem);
 }

 @Override
 public void updateOrder(FlightOrder order) {
  orderMapper.updateOrder(order);
 }


}
