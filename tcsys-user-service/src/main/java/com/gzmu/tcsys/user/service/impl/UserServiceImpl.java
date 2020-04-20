package com.gzmu.tcsys.user.service.impl;


import com.alibaba.dubbo.config.annotation.Service;
import com.gzmu.tcsys.bean.Member;
import com.gzmu.tcsys.bean.MemberReceiveAddress;
import com.gzmu.tcsys.service.UserService;
import com.gzmu.tcsys.user.mapper.MemberReceiveAddressMapper;
import com.gzmu.tcsys.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;

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
