package com.fh.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fh.model.Flight;
import com.fh.model.QueryObject;

import java.util.List;

public interface FilghtMapper extends BaseMapper<Flight> {
 Integer queryCount(QueryObject queryObject);

 List queryFlightList(QueryObject queryObject);

 List<Flight> queryFlightLists(Flight flight);
}
