package com.gzmu.tcsys.manage.util;

/**
 * 页面包装类
 *
 * @author weicaiwang
 */
public class Page {
    //当前页面
    private int pageNo = 1;
    //每页显示数量
    private int pageSize = 10;
    //总数
    private long totalCount = 0;
    //总页数
    private int totalPage = 1;

    public long getTotalPage() {
        return totalCount % pageSize == 0 ? totalCount / pageSize : totalCount / pageSize + 1;
    }

    public int limitStart() {
        return pageSize * (getPageNo() - 1);
    }

    public int limitEnd() {
        return pageSize;
    }

    public int getPageNo() {
        if(pageNo > getTotalPage()) {
            return totalPage;
        }
        return pageNo;
    }

    public Page setPageNo(int pageNo) {
        this.pageNo = pageNo;
        return this;
    }

    public int getPageSize() {
        return pageSize;
    }

    public Page setPageSize(int pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public Page setTotalCount(long totalCount) {
        this.totalCount = totalCount;
        return this;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
}