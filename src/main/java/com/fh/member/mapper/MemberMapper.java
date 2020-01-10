package com.fh.member.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fh.member.model.Member;

public interface MemberMapper extends BaseMapper<Member> {
    void addMember(Member member);

    Member queryMember(String name);
}
