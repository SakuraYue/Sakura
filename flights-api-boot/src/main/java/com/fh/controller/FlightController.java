package com.fh.controller;

import com.fh.common.ServerResponse;
import com.fh.model.Flight;
import com.fh.model.QueryObject;
import com.fh.service.FlightService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class FlightController {
 @Resource
 private FlightService flightService;
@RequestMapping("queryFlightList")
 public ServerResponse queryFlightList(QueryObject queryObject){
  return flightService.queryFlightList(queryObject);
 }
 @RequestMapping("queryFlightLists")
 public ServerResponse queryFlightLists(Flight flight){
  return flightService.queryFlightLists(flight);
 }
}
