package com.fh.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class FlightOrder {
private String  id;// 订单号(时间戳+雪花算法)
private Integer  userid;//  会员id
private Integer  status;//  订单状态 1代表未支付 2代表已支付
 @DateTimeFormat(pattern = "yyyy-MM-dd")
private Date createtime ;// 订单创建时间
private Integer  flightid ;// 航班id
 @DateTimeFormat(pattern = "yyyy-MM-dd")
private Date  paytime;//  支付时间
private Long  totalcount ;// 总票数
private BigDecimal totalprice;//  总票价
private Integer  paytype ;// 支付方式:1代表在线支付 2代表现金支付
}
