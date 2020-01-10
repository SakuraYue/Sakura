package com.fh.cart.model;

import java.math.BigDecimal;
import java.util.List;

public class Cart {

    private Long totalCount; //购物车中的商品总数量

    private BigDecimal totalPrice; //购物车中的商品的总价格

    private List<CartItem> cartItemList; //购物车中的商品列表

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

    public List<CartItem> getCartItemList() {
        return cartItemList;
    }

    public void setCartItemList(List<CartItem> cartItemList) {
        this.cartItemList = cartItemList;
    }
}
