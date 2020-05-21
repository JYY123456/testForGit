package com.fline.datasecurity.zj.dao.entity;

import java.util.Date;

public class SecurityHandlerRecord {
    private Integer id;

    private String taskcode;

    private String operation;

    private Date optime;

    private Integer opuser;

    private Integer datasource;

    private String targetlocation;

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

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation == null ? null : operation.trim();
    }

    public Date getOptime() {
        return optime;
    }

    public void setOptime(Date optime) {
        this.optime = optime;
    }

    public Integer getOpuser() {
        return opuser;
    }

    public void setOpuser(Integer opuser) {
        this.opuser = opuser;
    }

    public Integer getDatasource() {
        return datasource;
    }

    public void setDatasource(Integer datasource) {
        this.datasource = datasource;
    }

    public String getTargetlocation() {
        return targetlocation;
    }

    public void setTargetlocation(String targetlocation) {
        this.targetlocation = targetlocation == null ? null : targetlocation.trim();
    }
}