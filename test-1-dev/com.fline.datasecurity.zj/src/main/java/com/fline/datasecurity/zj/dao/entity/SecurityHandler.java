package com.fline.datasecurity.zj.dao.entity;

import java.util.Date;

public class SecurityHandler {
    private Integer id;

    private String taskcode;

    private Integer datasource;

    private String secretkey;

    private String targetlocation;

    private Integer state;

    private Integer handlertype;

    private Date lasttime;

    private String createdBy;

    private Date createdTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTaskcode() {
        return taskcode;
    }

    public void setTaskcode(String taskcode) {
        this.taskcode = taskcode == null ? null : taskcode.trim();
    }

    public Integer getDatasource() {
        return datasource;
    }

    public void setDatasource(Integer datasource) {
        this.datasource = datasource;
    }

    public String getSecretkey() {
        return secretkey;
    }

    public void setSecretkey(String secretkey) {
        this.secretkey = secretkey == null ? null : secretkey.trim();
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

    public Integer getHandlertype() {
        return handlertype;
    }

    public void setHandlertype(Integer handlertype) {
        this.handlertype = handlertype;
    }

    public Date getLasttime() {
        return lasttime;
    }

    public void setLasttime(Date lasttime) {
        this.lasttime = lasttime;
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
}