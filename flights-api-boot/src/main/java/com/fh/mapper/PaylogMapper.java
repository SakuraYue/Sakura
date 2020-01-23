package com.fh.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fh.model.Paylog;

public interface PaylogMapper extends BaseMapper<Paylog> {
 void addPayLog(Paylog payLog);

 void updatePayLog(Paylog payLog);

 Paylog getPayLogByOutTradeNo(String outTradeNo);
}
