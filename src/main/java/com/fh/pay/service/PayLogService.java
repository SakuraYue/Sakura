package com.fh.pay.service;


import com.fh.common.ServerResponse;
import com.fh.pay.model.PayLog;

public interface PayLogService {
    ServerResponse addPayLog(PayLog payLog);

    ServerResponse updatePayLog(PayLog payLog);

    ServerResponse getPayMoney(String outTradeNo);
}
