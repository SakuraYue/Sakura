package com.fh.order.model;

import java.math.BigDecimal;

public class OrderItem {

    private Integer id;//int  主键id

    private Integer memberId;//	int,会员的id

    private String orderId;//	varchar 订单的id

    private String productName;// 	varchar 商品名

    private Integer productId;//	int  商品id

    private String image;// varchar 商品的图片

    private Long total;//	  bigint 商品的数量

    private BigDecimal price;//	decimal 商品的价格

    private BigDecimal subtotalPrice;//	 decimal 商品的小计

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getSubtotalPrice() {
        return subtotalPrice;
    }

    public void setSubtotalPrice(BigDecimal subtotalPrice) {
        this.subtotalPrice = subtotalPrice;
    }
}
