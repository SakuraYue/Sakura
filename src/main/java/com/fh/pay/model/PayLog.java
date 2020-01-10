package com.fh.pay.model;

import java.math.BigDecimal;
import java.util.Date;

public class PayLog {

    private String outTradeNo;// varchar(50) primary key, #商户支付订单号

    private String orderId;// varchar(50), #订单号

    private String transactionId;// varchar(50), #微信支付订单号

    private Integer memberId;// int, #会员id

    private BigDecimal payMoney;// decimal(10,2), #支付金额

    private Date payTime;// datetime, #支付时间

    private Integer payStatus;// int(1), #支付状态 1:代表未支付 2:已支付

    private Integer payType;// int(1)#支付类型 1:代表微信支付 2:代表支付宝支付

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public BigDecimal getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(BigDecimal payMoney) {
        this.payMoney = payMoney;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public Integer getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }
}
