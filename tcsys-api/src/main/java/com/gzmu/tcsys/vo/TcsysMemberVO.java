package com.gzmu.tcsys.vo;

import com.gzmu.tcsys.bean.Member;

import java.io.Serializable;
import java.util.List;

/**
 * @author weicaiwang
 */
public class TcsysMemberVO implements Serializable {
    private List<Member> members;
    private Long total;

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}
