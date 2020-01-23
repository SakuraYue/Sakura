package com.fh.service;

import com.fh.common.ServerResponse;
import com.fh.model.Paylog;

public interface PaylogService {
 void addPayLog(Paylog payLog);

 void updatePayLog(Paylog payLog);

 ServerResponse getPayMoney(String outTradeNo);

}
