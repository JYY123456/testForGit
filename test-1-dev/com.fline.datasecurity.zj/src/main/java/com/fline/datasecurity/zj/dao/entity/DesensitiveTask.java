package com.fline.datasecurity.zj.dao.entity;

import java.util.Date;

public class DesensitiveTask {
    private Integer id;

    private String taskcode;

    private String datasource;

    private String executeuserlevel;

    private String executeusername;

    private String operationtables;

    private String desensitizationtemp;

    private Integer type;

    private String targetlocation;

    private String validtime;

    private Integer state;

    private Integer isapproval;

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

    public String getTaskcode() {
        return taskcode;
    }

    public void setTaskcode(String taskcode) {
        this.taskcode = taskcode == null ? null : taskcode.trim();
    }

    public String getDatasource() {
        return datasource;
    }

    public void setDatasource(String datasource) {
        this.datasource = datasource == null ? null : datasource.trim();
    }

    public String getExecuteuserlevel() {
        return executeuserlevel;
    }

    public void setExecuteuserlevel(String executeuserlevel) {
        this.executeuserlevel = executeuserlevel == null ? null : executeuserlevel.trim();
    }

    public String getExecuteusername() {
        return executeusername;
    }

    public void setExecuteusername(String executeusername) {
        this.executeusername = executeusername == null ? null : executeusername.trim();
    }

    public String getOperationtables() {
        return operationtables;
    }

    public void setOperationtables(String operationtables) {
        this.operationtables = operationtables == null ? null : operationtables.trim();
    }

    public String getDesensitizationtemp() {
        return desensitizationtemp;
    }

    public void setDesensitizationtemp(String desensitizationtemp) {
        this.desensitizationtemp = desensitizationtemp == null ? null : desensitizationtemp.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTargetlocation() {
        return targetlocation;
    }

    public void setTargetlocation(String targetlocation) {
        this.targetlocation = targetlocation == null ? null : targetlocation.trim();
    }

    public String getValidtime() {
        return validtime;
    }

    public void setValidtime(String validtime) {
        this.validtime = validtime == null ? null : validtime.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getIsapproval() {
        return isapproval;
    }

    public void setIsapproval(Integer isapproval) {
        this.isapproval = isapproval;
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