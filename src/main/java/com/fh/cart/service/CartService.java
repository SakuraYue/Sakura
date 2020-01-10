package com.fh.cart.service;

import com.fh.common.ServerResponse;
import com.fh.member.model.Member;

public interface CartService {
    ServerResponse buyCart(Integer productId, Member member);

    ServerResponse batchDeleteCartItem(Member member);

    ServerResponse deleteCartItem(Integer productId, Member member);

    ServerResponse changeCartItemCount(Integer productId, Integer count, Member member);

    ServerResponse changeAllCartItemCheckedStatus(Boolean checked, Member member);

    ServerResponse changeCartItemCheckedStatus(Integer productId, Member member);

    ServerResponse queryCart(Member member);

    ServerResponse getCartTotalCount(Member member);

    ServerResponse queryCheckedCart(Member member);
}
