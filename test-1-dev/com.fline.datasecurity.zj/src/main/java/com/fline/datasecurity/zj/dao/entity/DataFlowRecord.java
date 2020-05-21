package com.fline.datasecurity.zj.dao.entity;

import java.util.Date;

public class DataFlowRecord {
    private Integer id;

    private Integer userid;

    private String userlevel;

    private String datasource;

    private String dataacount;

    private Integer optype;

    private String optime;

    private String targetlocation;

    private Integer state;

    private String memo;

    private String createdBy;

    private Date createdTime;

    private String updatedBy;

    private Date updatedTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUserlevel() {
        return userlevel;
    }

    public void setUserlevel(String userlevel) {
        this.userlevel = userlevel == null ? null : userlevel.trim();
    }

    public String getDatasource() {
        return datasource;
    }

    public void setDatasource(String datasource) {
        this.datasource = datasource == null ? null : datasource.trim();
    }

    public String getDataacount() {
        return dataacount;
    }

    public void setDataacount(String dataacount) {
        this.dataacount = dataacount == null ? null : dataacount.trim();
    }

    public Integer getOptype() {
        return optype;
    }

    public void setOptype(Integer optype) {
        this.optype = optype;
    }

    public String getOptime() {
        return optime;
    }

    public void setOptime(String optime) {
        this.optime = optime == null ? null : optime.trim();
    }

    public String getTargetlocation() {
        return targetlocation;
    }

    public void setTargetlocation(String targetlocation) {
        this.targetlocation = targetlocation == null ? null : targetlocation.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy == null ? null : createdBy.trim();
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy == null ? null : updatedBy.trim();
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }
}