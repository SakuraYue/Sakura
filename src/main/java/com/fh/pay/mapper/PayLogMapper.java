package com.fh.pay.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fh.pay.model.PayLog;

public interface PayLogMapper extends BaseMapper<PayLog> {
    void updatePayLog(PayLog payLog);

    PayLog getPayLogByOutTradeNo(String outTradeNo);
}
