package com.fh.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fh.common.ResponseEnum;
import com.fh.common.ServerResponse;
import com.fh.mapper.MemberMapper;
import com.fh.model.Member;
import com.fh.util.JwtUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class MemberServiceImpl implements MemberService {
 @Resource
 private MemberMapper memberMapper;
 @Resource
 private RedisTemplate redisTemplate;

 @Override
 public ServerResponse login(Member member) {
  if (StringUtils.isBlank(member.getUsername())){
   return ServerResponse.error(ResponseEnum.MEMBERNAME_IS_NULL);
  }
  if (StringUtils.isBlank(member.getUsrpassword())){
   return ServerResponse.error(ResponseEnum.MEMBERPASSWORD_IS_NULL);
  }
  //判断用户名是否正确
  Member memberdb = memberMapper.selectMemberByName(member.getUsername());
  if (memberdb == null){
   return ServerResponse.error(ResponseEnum.MEMBERNAME_IS_ERROR);
  }

  //判断密码是否正确
  QueryWrapper<Member> queryPasswordWrapper=new QueryWrapper<>();
  queryPasswordWrapper.eq("usrpassword",member.getUsrpassword());
  Member memberPassword = memberMapper.selectOne(queryPasswordWrapper);
  if (!memberPassword.getUsrpassword().equals(member.getUsrpassword())){
   return ServerResponse.error(ResponseEnum.MEMBERPASSWORD_IS_ERROR);
  }

  //生成TOKEN
  Member loginMember = new Member();
  loginMember.setUuid(UUID.randomUUID().toString());
  loginMember.setId(memberdb.getId());
  loginMember.setUsername(memberdb.getUsername());

  String token = JwtUtil.createToken(loginMember);


  redisTemplate.opsForValue().set("member:" + loginMember.getUuid(),232324,30, TimeUnit.MINUTES);
  return ServerResponse.success(token);
 }

 @Override
 public Member getMemberById(Integer id) {
  return memberMapper.getMemberById(id);
 }
}
