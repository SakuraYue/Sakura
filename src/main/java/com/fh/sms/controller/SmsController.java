package com.fh.sms.controller;

import com.fh.common.ServerResponse;
import com.fh.sms.service.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("sms")
@CrossOrigin
@RestController
public class SmsController {
    @Autowired
    private SmsService smsService;

    @RequestMapping("getCode")
    public ServerResponse getCode(String phone){
        return smsService.getCode(phone);
    }
}
