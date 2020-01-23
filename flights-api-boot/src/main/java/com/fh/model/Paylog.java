package com.fh.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class Paylog {
 private String outTradeNo;// varchar(50) primary key, #商户支付订单号

 private String orderId;// varchar(50), #订单号

 private String transactionId;// varchar(50), #微信支付订单号

 private Integer memberId;// int, #会员id

 private BigDecimal payMoney;// decimal(10,2), #支付金额

 private Integer payStatus;// int(1), #支付状态 1:代表未支付 2:已支付

 private Integer payType;// int(1)#支付类型 1:代表微信支付 2:代表支付宝支付

 private Date payTime;

}
