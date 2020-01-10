package com.fh.pay.service;

import com.fh.common.ResponseEnum;
import com.fh.common.ServerResponse;
import com.fh.pay.mapper.PayLogMapper;
import com.fh.pay.model.PayLog;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PayLogServiceImpl implements PayLogService {

    @Resource
    private PayLogMapper payLogMapper;

    @Override
    public ServerResponse addPayLog(PayLog payLog) {
        payLogMapper.insert(payLog);
        return ServerResponse.success();
    }

    @Override
    public ServerResponse updatePayLog(PayLog payLog) {
        payLogMapper.updatePayLog(payLog);
        return ServerResponse.success();
    }

    @Override
    public ServerResponse getPayMoney(String outTradeNo) {
        PayLog payLog = payLogMapper.getPayLogByOutTradeNo(outTradeNo);
        if(payLog == null){
            return ServerResponse.error(ResponseEnum.PAY_ORDER_NO_EXIST);
        }
        if(payLog.getPayStatus() == 1){
            return ServerResponse.error();
        }
        return ServerResponse.success(payLog.getPayMoney());
    }
}
