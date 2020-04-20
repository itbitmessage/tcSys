package com.gzmu.tcsys.user.service;

import com.gzmu.tcsys.user.bean.Member;
import com.gzmu.tcsys.user.bean.MemberReceiveAddress;
import com.gzmu.tcsys.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
