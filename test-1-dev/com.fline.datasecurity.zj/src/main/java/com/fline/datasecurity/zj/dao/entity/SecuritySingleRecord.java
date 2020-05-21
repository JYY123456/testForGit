package com.fline.datasecurity.zj.dao.entity;

public class SecuritySingleRecord {
    private Integer id;

    private String singletaskcode;

    private Integer userid;

    private Integer operation;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSingletaskcode() {
        return singletaskcode;
    }

    public void setSingletaskcode(String singletaskcode) {
        this.singletaskcode = singletaskcode == null ? null : singletaskcode.trim();
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getOperation() {
        return operation;
    }

    public void setOperation(Integer operation) {
        this.operation = operation;
    }
}