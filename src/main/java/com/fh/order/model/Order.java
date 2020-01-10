package com.fh.order.model;

import java.math.BigDecimal;
import java.util.Date;

public class Order {

    private String id;//varchar(50),订单id

    private Integer memberId;//	int 会员id，这个订单属于哪个会员

    private Integer status;//	int(1) 订单状态:1代表未付款 2代表已付款

    private Date createTime;//	datetime 订单的创建时间

    private Date payTime;//	datetime 订单的支付时间

    private Long totalCount;//	bigint 订单中商品的总数量

    private BigDecimal totalPrice;//	decimal 订单中商品的总金额

    private Integer payType;//	int 支付方式，1代表在线支付 2代表货到付款

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }
}
