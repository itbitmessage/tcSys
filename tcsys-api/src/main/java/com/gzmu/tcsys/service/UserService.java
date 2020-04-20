package com.gzmu.tcsys.service;

import com.gzmu.tcsys.bean.Member;
import com.gzmu.tcsys.bean.MemberReceiveAddress;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author weicaiwang
 */
@Service
public interface UserService {


    List<Member> getAllUser();

    List<MemberReceiveAddress> getReceiveAddressByMemberId(String memberId);
}
