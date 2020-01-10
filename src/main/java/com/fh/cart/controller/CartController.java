package com.fh.cart.controller;

import com.fh.cart.service.CartService;
import com.fh.common.MemberAnnotation;
import com.fh.common.ServerResponse;
import com.fh.member.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("cart")
@RestController
public class CartController {
    @Autowired
    private CartService cartService;

    @RequestMapping("buyCart")
    public ServerResponse buyCart(Integer productId, @MemberAnnotation Member member){
        return cartService.buyCart(productId,member);
    }

    @RequestMapping("getCartTotalCount")
    public ServerResponse getCartTotalCount(@MemberAnnotation Member member){
        return cartService.getCartTotalCount(member);
    }

    @RequestMapping("queryCart")
    public ServerResponse queryCart(@MemberAnnotation Member member){
        return cartService.queryCart(member);
    }

    @RequestMapping("changeCartItemCheckedStatus")
    public ServerResponse changeCartItemCheckedStatus(Integer productId,@MemberAnnotation Member member){
        return cartService.changeCartItemCheckedStatus(productId,member);
    }

    @RequestMapping("changeAllCartItemCheckedStatus")
    public ServerResponse changeAllCartItemCheckedStatus(Boolean checked,@MemberAnnotation Member member){
        return cartService.changeAllCartItemCheckedStatus(checked,member);
    }

    @RequestMapping("changeCartItemCount")
    public ServerResponse changeCartItemCount(Integer productId,Integer count,@MemberAnnotation Member member){
        return cartService.changeCartItemCount(productId,count,member);
    }

    @RequestMapping("deleteCartItem")
    public ServerResponse deleteCartItem(Integer productId,@MemberAnnotation Member member){
        return cartService.deleteCartItem(productId,member);
    }

    @RequestMapping("batchDeleteCartItem")
    public ServerResponse batchDeleteCartItem(@MemberAnnotation Member member){
        return cartService.batchDeleteCartItem(member);
    }

    @RequestMapping("queryCheckedCart")
    public ServerResponse queryCheckedCart(@MemberAnnotation Member member){
        return cartService.queryCheckedCart(member);
    }

}
