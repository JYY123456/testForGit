package com.fline.datasecurity.zj.dao.entity;

import java.util.Date;

public class DataSource {
    private Integer id;

    private String datasource;

    private String dbtype;

    private String dburl;

    private String untitled3;

    private String createdBy;

    private Date createdTime;

    private String updatedBy;

    private Date updatedTime;

    private String untitled2;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDatasource() {
        return datasource;
    }

    public void setDatasource(String datasource) {
        this.datasource = datasource == null ? null : datasource.trim();
    }

    public String getDbtype() {
        return dbtype;
    }

    public void setDbtype(String dbtype) {
        this.dbtype = dbtype == null ? null : dbtype.trim();
    }

    public String getDburl() {
        return dburl;
    }

    public void setDburl(String dburl) {
        this.dburl = dburl == null ? null : dburl.trim();
    }

    public String getUntitled3() {
        return untitled3;
    }

    public void setUntitled3(String untitled3) {
        this.untitled3 = untitled3 == null ? null : untitled3.trim();
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

    public String getUntitled2() {
        return untitled2;
    }

    public void setUntitled2(String untitled2) {
        this.untitled2 = untitled2 == null ? null : untitled2.trim();
    }
}