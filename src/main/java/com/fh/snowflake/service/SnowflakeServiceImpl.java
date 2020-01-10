package com.fh.snowflake.service;

import com.fh.common.ServerResponse;
import com.fh.util.IdUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Service
public class SnowflakeServiceImpl implements SnowflakeService {

    @Resource
    private RedisTemplate redisTemplate;

    public ServerResponse getId(){
        String id1 = IdUtil.createId();
        redisTemplate.opsForValue().set(id1,id1,3, TimeUnit.MINUTES);
        return ServerResponse.success(id1);
    }

}
