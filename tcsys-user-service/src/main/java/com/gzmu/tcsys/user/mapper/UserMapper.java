package com.gzmu.tcsys.user.mapper;




import com.gzmu.tcsys.bean.Member;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author weicaiwang
 */

public interface UserMapper extends Mapper<Member> {

    List<Member> selectAllUser();
}
