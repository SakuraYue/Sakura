package com.fh.snowflake.controller;

import com.fh.common.ServerResponse;
import com.fh.snowflake.service.SnowflakeService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RequestMapping("snowflake")
@RestController
public class SnowflakeController {

    @Resource
    private SnowflakeService snowflakeService;

    @RequestMapping("getId")
    public ServerResponse getId(){
        return snowflakeService.getId();
    }


}
