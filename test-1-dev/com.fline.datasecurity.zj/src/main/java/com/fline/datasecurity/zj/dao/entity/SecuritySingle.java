package com.fline.datasecurity.zj.dao.entity;

import java.util.Date;

public class SecuritySingle {
    private Integer id;

    private String taskcode;

    private String originaldata;

    private String secretkey;

    private String ciphertext;

    private Integer operation;

    private String datalevel;

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

    public String getOriginaldata() {
        return originaldata;
    }

    public void setOriginaldata(String originaldata) {
        this.originaldata = originaldata == null ? null : originaldata.trim();
    }

    public String getSecretkey() {
        return secretkey;
    }

    public void setSecretkey(String secretkey) {
        this.secretkey = secretkey == null ? null : secretkey.trim();
    }

    public String getCiphertext() {
        return ciphertext;
    }

    public void setCiphertext(String ciphertext) {
        this.ciphertext = ciphertext == null ? null : ciphertext.trim();
    }

    public Integer getOperation() {
        return operation;
    }

    public void setOperation(Integer operation) {
        this.operation = operation;
    }

    public String getDatalevel() {
        return datalevel;
    }

    public void setDatalevel(String datalevel) {
        this.datalevel = datalevel == null ? null : datalevel.trim();
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