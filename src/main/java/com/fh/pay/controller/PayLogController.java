package com.fh.pay.controller;

import com.fh.common.ServerResponse;
import com.fh.pay.service.PayLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("payLog")
public class PayLogController {

    @Autowired
    private PayLogService payLogService;

    @RequestMapping("getPayMoney")
    public ServerResponse getPayMoney(String outTradeNo){
        return payLogService.getPayMoney(outTradeNo);
    }

}
