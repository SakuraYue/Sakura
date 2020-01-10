package com.fh.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fh.order.model.Order;
import com.fh.order.model.OrderItem;

import java.util.List;

public interface OrderMapper extends BaseMapper<Order> {
    void addOrder(Order order);

    void addOrderItemList(List<OrderItem> orderItemList);
}
