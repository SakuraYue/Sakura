package com.fh.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fh.model.Member;

public interface MemberMapper extends BaseMapper<Member> {
 Member getMemberById(Integer id);

 Member selectMemberByName(String username);
}
