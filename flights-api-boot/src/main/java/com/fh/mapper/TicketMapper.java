package com.fh.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fh.model.Ticket;

import java.util.List;

public interface TicketMapper extends BaseMapper<Ticket> {

 List<Ticket> selectTicketByFlightId(Integer id);
}
