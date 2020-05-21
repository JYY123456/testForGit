package com.fline.datasecurity.zj.vo;

import java.util.List;

import lombok.Getter;

/**
 * 对分页的内容进行封装
 */

@Getter
public class PageResult<T> {
    private List<T> result;//每一页的结果集
    private int totalCount;//结果总数

    private int currentPage  = 1;//当前页
    private int pageSize = 10; //每一页最多有多少条数据

    //下面三个需要计算出来
    private int prevPage;//上一页
    private int nextPage;//下一页
    private int totalPage;//最后一页（一共多少页）

    public PageResult(List<T> result, int totalCount, int currentPage, int pageSize) {
        this.result = result;
        this.totalCount = totalCount;
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        //最后一页
        this.totalPage = totalCount % pageSize == 0 ? totalCount / pageSize : totalCount / pageSize+1;
        //上一页
        this.prevPage = currentPage-1 >= 1 ? currentPage-1: 1;
        this.prevPage = this.prevPage <= 0 ? 1 : this.prevPage;
        //下一页
        this.nextPage = currentPage+1 <= totalPage ? currentPage + 1 : totalPage;
        this.nextPage = this.nextPage <= 0 ? 1 : this.nextPage;
        //安全设置当前页，最多只能到最后一页
        this.currentPage = currentPage > totalPage ? totalPage : currentPage;
        this.currentPage = this.currentPage <= 0 ? 1 : this.currentPage;
    }
}
