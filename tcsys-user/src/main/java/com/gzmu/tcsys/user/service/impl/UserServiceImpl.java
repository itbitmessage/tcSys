package com.gzmu.tcsys.user.service.impl;

import com.gzmu.tcsys.user.bean.Member;
import com.gzmu.tcsys.user.bean.MemberReceiveAddress;
import com.gzmu.tcsys.user.mapper.MemberReceiveAddressMapper;
import com.gzmu.tcsys.user.mapper.UserMapper;
import com.gzmu.tcsys.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author weicaiwang
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    MemberReceiveAddressMapper memberReceiveAddressMapper;

    @Override
    public List<Member> getAllUser() {
        //List<Member> Members = userMapper.selectAllUser();

        List<Member> members = userMapper.selectAll();
        return members;
    }

    @Override
    public List<MemberReceiveAddress> getReceiveAddressByMemberId(String memberId) {
        Example example = new Example( MemberReceiveAddress.class );
        example.createCriteria().andEqualTo( "memberId",memberId );
        List<MemberReceiveAddress> memberReceiveAddresses = memberReceiveAddressMapper.selectByExample( example );
        return memberReceiveAddresses;
    }
}
