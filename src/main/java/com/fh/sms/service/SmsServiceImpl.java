package com.fh.sms.service;

import com.fh.common.ResponseEnum;
import com.fh.common.ServerResponse;
import com.fh.util.RedisUtil;
import com.fh.util.SendMsgUtil;
import com.fh.util.SystemConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class SmsServiceImpl implements SmsService {
    @Autowired
    private RedisTemplate redisTemplate;


    @Override
    public ServerResponse getCode(String phone) {
        if (phone==null) {
            return ServerResponse.error(ResponseEnum.PHONE_IS_NULL);
        }
        String s = SendMsgUtil.sendCode(phone);
        if(s==null){
            return ServerResponse.error(ResponseEnum.PHONE_ERROR);
        }
        redisTemplate.opsForValue().set(SystemConstant.CODE+phone,s);
        RedisUtil.expire(SystemConstant.CODE+phone,SystemConstant.CODE_OUT_TIME);
        return ServerResponse.success();
    }
}