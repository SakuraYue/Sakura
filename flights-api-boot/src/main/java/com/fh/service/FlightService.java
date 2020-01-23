package com.fh.service;

import com.fh.common.ServerResponse;
import com.fh.model.Flight;
import com.fh.model.QueryObject;

public interface FlightService {
 ServerResponse queryFlightList(QueryObject queryObject);

 ServerResponse queryFlightLists(Flight flight);
}
