package com.fh.order.service;

import com.fh.common.ServerResponse;
import com.fh.member.model.Member;
import com.fh.order.model.Order;

public interface OrderService {
    ServerResponse addOrder(Member member);

    void updateOrder(Order order);
}
