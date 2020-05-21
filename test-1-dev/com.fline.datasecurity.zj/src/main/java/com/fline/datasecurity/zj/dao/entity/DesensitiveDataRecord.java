package com.fline.datasecurity.zj.dao.entity;

import java.util.Date;

public class DesensitiveDataRecord {
    private Integer id;

    private Integer user;

    private String userlevel;

    private Integer taskrecordid;

    private String dataresourceid;

    private String datafields;

    private String targetlocation;

    private Integer type;

    private Integer templateid;

    private Integer methodid;

    private Date createdTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser() {
        return user;
    }

    public void setUser(Integer user) {
        this.user = user;
    }

    public String getUserlevel() {
        return userlevel;
    }

    public void setUserlevel(String userlevel) {
        this.userlevel = userlevel == null ? null : userlevel.trim();
    }

    public Integer getTaskrecordid() {
        return taskrecordid;
    }

    public void setTaskrecordid(Integer taskrecordid) {
        this.taskrecordid = taskrecordid;
    }

    public String getDataresourceid() {
        return dataresourceid;
    }

    public void setDataresourceid(String dataresourceid) {
        this.dataresourceid = dataresourceid == null ? null : dataresourceid.trim();
    }

    public String getDatafields() {
        return datafields;
    }

    public void setDatafields(String datafields) {
        this.datafields = datafields == null ? null : datafields.trim();
    }

    public String getTargetlocation() {
        return targetlocation;
    }

    public void setTargetlocation(String targetlocation) {
        this.targetlocation = targetlocation == null ? null : targetlocation.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getTemplateid() {
        return templateid;
    }

    public void setTemplateid(Integer templateid) {
        this.templateid = templateid;
    }

    public Integer getMethodid() {
        return methodid;
    }

    public void setMethodid(Integer methodid) {
        this.methodid = methodid;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }
}