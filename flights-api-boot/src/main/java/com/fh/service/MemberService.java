package com.fh.service;

import com.fh.common.ServerResponse;
import com.fh.model.Member;

public interface MemberService {
 ServerResponse login(Member member);

 Member getMemberById(Integer id);
}
