package com.fh.service;

import com.fh.common.ServerResponse;
import com.fh.mapper.AreaMapper;
import com.fh.mapper.FilghtMapper;
import com.fh.mapper.PlaneMapper;
import com.fh.mapper.TicketMapper;
import com.fh.model.Flight;
import com.fh.model.QueryObject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FlightServiceImpl implements FlightService {
 @Resource
 private FilghtMapper filghtMapper;
 @Resource
 private PlaneMapper planeMapper;
 @Resource
 private TicketMapper ticketMapper;
 @Resource
 private AreaMapper areaMapper;

 @Override
 public ServerResponse queryFlightList(QueryObject queryObject) {
  Map map = new HashMap();
  Integer total = filghtMapper.queryCount(queryObject);
  List flightMap = filghtMapper.queryFlightList(queryObject);
  map.put("draw", queryObject.getDraw());
  map.put("recordsTotal", total);
  map.put("recordsFiltered", total);
  map.put("data", flightMap);
  return ServerResponse.success(map);
 }

 @Override
 public ServerResponse queryFlightLists(Flight flight) {
  List<Flight> flightMap = filghtMapper.queryFlightLists(flight);
  return ServerResponse.success(flightMap);
 }
}
