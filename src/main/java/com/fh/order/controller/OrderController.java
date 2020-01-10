package com.fh.order.controller;

import com.fh.common.MemberAnnotation;
import com.fh.common.ServerResponse;
import com.fh.member.model.Member;
import com.fh.order.service.OrderService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RequestMapping("order")
@RestController
public class OrderController {
    @Resource
    private OrderService orderService;

    @RequestMapping("addOrder")
    public ServerResponse addOrder(@MemberAnnotation Member member){
        return orderService.addOrder(member);
    }

}
