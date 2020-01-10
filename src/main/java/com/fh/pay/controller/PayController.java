package com.fh.pay.controller;

import com.fh.common.MemberAnnotation;
import com.fh.common.ServerResponse;
import com.fh.member.model.Member;
import com.fh.pay.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("pay")
public class PayController {

    @Autowired
    private PayService payService;

    //创建微信支付预支付订单
    @RequestMapping("createNative")
    public ServerResponse createNative(@MemberAnnotation Member member){
        return payService.createNative(member.getId());
    }

    //查询微信支付预支付订单状态
    @RequestMapping("queryPayStatus")
    public ServerResponse queryPayStatus(@MemberAnnotation Member member) {
        return payService.queryPayStatus(member.getId());
    }

}
