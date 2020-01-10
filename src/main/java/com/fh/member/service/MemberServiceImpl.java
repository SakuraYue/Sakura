package com.fh.member.service;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fh.common.ResponseEnum;
import com.fh.common.ServerResponse;
import com.fh.member.mapper.MemberMapper;
import com.fh.member.model.Member;
import com.fh.util.JwtUtil;
import com.fh.util.SystemConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Resource
    private MemberMapper memberMapper;
    @Override
    public ServerResponse addMember(Member member) {
        if (member==null) {
            return ServerResponse.error(ResponseEnum.MEMBER_IS_NULL);
        }
        if (!member.getCode().equals(redisTemplate.opsForValue().get(SystemConstant.CODE+member.getPhone()))) {
            return ServerResponse.error(ResponseEnum.CODE_ERROR);
        }
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("name",member.getName());
        Member member1 = memberMapper.selectOne(queryWrapper);
        if(member1!=null){
            return ServerResponse.error(ResponseEnum.MEMBERNAME_IS_EXIST);
        }
        QueryWrapper queryWrapperByPhone = new QueryWrapper();
        queryWrapperByPhone.eq("phone",member.getPhone());
        Member member2 = memberMapper.selectOne(queryWrapperByPhone);
        if(member2.getPhone().equals(member.getPhone())){
            return ServerResponse.error(ResponseEnum.PHONE_IS_EXIST);
        }
        memberMapper.addMember(member);
        return ServerResponse.success();
    }
    @Override
    public ServerResponse login(String name,String password) {
        if (name==null) {
            return ServerResponse.error(ResponseEnum.MEMBERNAME_IS_NULL);
        }
        if(password==null){
            return ServerResponse.error(ResponseEnum.MEMBERPASSWORD_IS_NULL);
        }
        Member member1 = memberMapper.queryMember(name);
        if(member1==null){
            return ServerResponse.error(ResponseEnum.MEMBERNAME_IS_ERROR);
        }
        if (!member1.getPassword().equals(password)){
            return ServerResponse.error(ResponseEnum.MEMBERPASSWORD_IS_ERROR);
        }
            String jsonString = JSON.toJSONString(member1);
            String token = JwtUtil.createJWT(member1.getId().toString(), jsonString, SystemConstant.TOKEN_OUT_TIME);
            redisTemplate.opsForValue().set("token",member1);
            return ServerResponse.success(token);
        }

    @Override
    public ServerResponse getMember(HttpServletRequest request) {
        Member member = (Member) request.getSession().getAttribute(SystemConstant.LOGGIN_CURRENT_USER);
        if(member==null){
            return ServerResponse.error();
        }
        return ServerResponse.success(member.getName());
    }
}
