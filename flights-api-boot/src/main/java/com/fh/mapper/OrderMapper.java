package com.fh.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fh.model.FlightOrder;

public interface OrderMapper extends BaseMapper<FlightOrder> {
 FlightOrder getOrderByMemberId(Integer id);

 void addOrder(FlightOrder flightOrder);

 void updateOrder(FlightOrder order);
}
