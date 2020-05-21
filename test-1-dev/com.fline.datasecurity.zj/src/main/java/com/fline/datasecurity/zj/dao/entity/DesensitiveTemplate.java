package com.fline.datasecurity.zj.dao.entity;

import java.util.Date;

public class DesensitiveTemplate {
    private Integer id;

    private String tempname;

    private Integer describe;

    private String fields;

    private String desensitizationrule;

    private Integer isactive;

    private String createdBy;

    private Date createdTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTempname() {
        return tempname;
    }

    public void setTempname(String tempname) {
        this.tempname = tempname == null ? null : tempname.trim();
    }

    public Integer getDescribe() {
        return describe;
    }

    public void setDescribe(Integer describe) {
        this.describe = describe;
    }

    public String getFields() {
        return fields;
    }

    public void setFields(String fields) {
        this.fields = fields == null ? null : fields.trim();
    }

    public String getDesensitizationrule() {
        return desensitizationrule;
    }

    public void setDesensitizationrule(String desensitizationrule) {
        this.desensitizationrule = desensitizationrule == null ? null : desensitizationrule.trim();
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
}