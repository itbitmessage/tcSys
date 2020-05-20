package com.gzmu.tcsys.user.service.impl;


import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gzmu.tcsys.bean.Member;
import com.gzmu.tcsys.bean.MemberReceiveAddress;
import com.gzmu.tcsys.service.UserService;
import com.gzmu.tcsys.user.mapper.MemberReceiveAddressMapper;
import com.gzmu.tcsys.user.mapper.UserMapper;
import com.gzmu.tcsys.vo.TcsysMemberVO;
import org.springframework.beans.factory.annotation.Autowired;

import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Date;
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

    /**
     * 会员查询
     *
     * @param member
     * @param pageNum
     * @param pageSize
     * @param startTime
     * @param endTime
     * @return
     */
    @Override
    public TcsysMemberVO getMember(Member member, Integer pageNum, Integer pageSize, String startTime, String endTime) {
        PageHelper.startPage( pageNum, pageSize );
        Example example = new Example( Member.class );
        Example.Criteria criteria = example.createCriteria();

        if (null != member) {
            String username = member.getUsername();
            if (!username.isEmpty()) {
                criteria.andLike( "username", "%"+username+"%" );
            }
            if (!member.getId().isEmpty()){
                criteria.andEqualTo( "id",member.getId() );
            }
        }
        if (null != startTime && null != endTime) {
            criteria.andBetween( "createTime", startTime, endTime );
        }
        List<Member> memberList = userMapper.selectByExample( example );
        PageInfo<Member> pageInfo = new PageInfo<>( memberList );
        TcsysMemberVO tcsysMemberVO = new TcsysMemberVO();
        tcsysMemberVO.setMembers( new ArrayList<Member>( memberList ) );
        tcsysMemberVO.setTotal( pageInfo.getTotal() );
        return tcsysMemberVO;
    }


    /**
     * 增加member
     *
     * @param member
     * @return
     */
    @Override
    public Boolean add(Member member) {
        return null;
    }

    /**
     * 根据id删除Member
     *
     * @param id
     * @return
     */
    @Override
    public Boolean deleteMember(String id) {
        return null;
    }

    /**
     * 修改member信息
     *
     * @param member
     * @return
     */
    @Override
    public Boolean update(Member member) {
        return null;
    }

    public List<Member> getAllUser() {
        //List<Member> Members = userMapper.selectAllUser();

        List<Member> members = userMapper.selectAll();
        return members;
    }

    public List<MemberReceiveAddress> getReceiveAddressByMemberId(String memberId) {
        Example example = new Example( MemberReceiveAddress.class );
        example.createCriteria().andEqualTo( "memberId", memberId );
        List<MemberReceiveAddress> memberReceiveAddresses = memberReceiveAddressMapper.selectByExample( example );
        return memberReceiveAddresses;
    }

    public Member getUserById(String id) {
        Member member = userMapper.selectByPrimaryKey( id );
        return member;
    }

    public Boolean editMember(Member member) {
        int primaryKey = userMapper.updateByPrimaryKeySelective( member );
        System.out.println( primaryKey );
        return primaryKey > 0;
    }

    public Boolean delMemberById(String id) {
        int delete = userMapper.deleteByPrimaryKey( id );
        return delete > 0;
    }

}
