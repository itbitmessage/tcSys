package com.gzmu.tcsys.vo;

import java.io.Serializable;

/**
 * 等级Vo
 *
 * @author weicaiwang
 */
public class TcsysCatalogGradeVO implements Serializable {

    private Object calatlog;

    private Integer grade;

    public Object getCalatlog() {
        return calatlog;
    }

    public void setCalatlog(Object calatlog) {
        this.calatlog = calatlog;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }
}
