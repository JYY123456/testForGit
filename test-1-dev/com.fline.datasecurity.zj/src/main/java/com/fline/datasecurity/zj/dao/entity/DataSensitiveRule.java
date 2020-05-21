package com.fline.datasecurity.zj.dao.entity;

import java.util.Date;

public class DataSensitiveRule {
    private Integer id;

    private String rulename;

    private String rulecode;

    private String template;

    private String fieldnames;

    private Integer isactive;

    private String createdBy;

    private Date createdTime;

    private String updatedBy;

    private Date updatedTime;

    private String ruledescribe;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRulename() {
        return rulename;
    }

    public void setRulename(String rulename) {
        this.rulename = rulename == null ? null : rulename.trim();
    }

    public String getRulecode() {
        return rulecode;
    }

    public void setRulecode(String rulecode) {
        this.rulecode = rulecode == null ? null : rulecode.trim();
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template == null ? null : template.trim();
    }

    public String getFieldnames() {
        return fieldnames;
    }

    public void setFieldnames(String fieldnames) {
        this.fieldnames = fieldnames == null ? null : fieldnames.trim();
    }

    public Integer getIsactive() {
        return isactive;
    }

    public void setIsactive(Integer isactive) {
        this.isactive = isactive;
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

    public String getRuledescribe() {
        return ruledescribe;
    }

    public void setRuledescribe(String ruledescribe) {
        this.ruledescribe = ruledescribe == null ? null : ruledescribe.trim();
    }
}