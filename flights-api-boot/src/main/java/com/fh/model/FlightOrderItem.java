package com.fh.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class FlightOrderItem {
private Integer  id;// 订单详情id，自增
private Integer  userid ;// 会员id
private String  orderid ;// 订单id
private Integer  ticketid ;// 机票id
private Integer  tickettype;//  机票类型 1代表头等舱，2代表经济舱
private BigDecimal price;//  票价
private String  realname ;// 乘机人姓名
private String  idcard;//  乘机人身份证号码
}
