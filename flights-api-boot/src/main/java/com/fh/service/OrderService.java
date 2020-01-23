package com.fh.service;

import com.fh.common.ServerResponse;
import com.fh.model.FlightOrder;

import java.math.BigDecimal;

public interface OrderService {

 ServerResponse queryTicket(Integer id);

 ServerResponse getOrderByMemberId(Integer id);

 ServerResponse toOrder(Integer memberid, Integer id);

 ServerResponse addOrder(Integer id, Integer flighttype, BigDecimal price, String username, String idcart, Integer countperpose, BigDecimal subprice);

 ServerResponse getOrderByUserId(Integer id);

 void updateOrder(FlightOrder order);
}
