package com.fline.datasecurity.zj.dao.entity;

public class DesensitiveTaskRecord {
    private Integer id;

    private String taskcode;

    private String taskoperationtime;

    private String consumingtime;

    private Integer state;

    private String operationuser;

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

    public String getTaskoperationtime() {
        return taskoperationtime;
    }

    public void setTaskoperationtime(String taskoperationtime) {
        this.taskoperationtime = taskoperationtime == null ? null : taskoperationtime.trim();
    }

    public String getConsumingtime() {
        return consumingtime;
    }

    public void setConsumingtime(String consumingtime) {
        this.consumingtime = consumingtime == null ? null : consumingtime.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getOperationuser() {
        return operationuser;
    }

    public void setOperationuser(String operationuser) {
        this.operationuser = operationuser == null ? null : operationuser.trim();
    }
}