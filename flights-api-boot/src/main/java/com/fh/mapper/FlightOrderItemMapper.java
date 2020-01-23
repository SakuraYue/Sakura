package com.fh.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fh.model.FlightOrderItem;

public interface FlightOrderItemMapper extends BaseMapper<FlightOrderItemMapper> {
 void addorderItem(FlightOrderItem flightOrderItem);

 FlightOrderItem getOrderByUserId(Integer id);
}
