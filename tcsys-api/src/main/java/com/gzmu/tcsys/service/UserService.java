package com.gzmu.tcsys.service;

import com.gzmu.tcsys.bean.Member;
import com.gzmu.tcsys.vo.TcsysMemberVO;
import java.util.Date;
import org.springframework.stereotype.Service;


/**
 * @author weicaiwang
 */
@Service
public interface UserService {

    /**
     * 会员查询
     * @param member
     * @param pageNum
     * @param pageSize
     * @param startTime
     * @param endTime
     * @return
     */
    TcsysMemberVO getMember(Member member, Integer pageNum, Integer pageSize,String startTime,String endTime);

    /**
     * 增加member
     * @param member
     * @return
     */
    Boolean add(Member member);

    /**
     * 根据id删除Member
     * @param id
     * @return
     */
    Boolean deleteMember(String id);

    /**
     * 修改member信息
     * @param member
     * @return
     */
    Boolean update(Member member);

}
