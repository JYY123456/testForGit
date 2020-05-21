package com.fline.datasecurity.zj.dao.entity;

import java.util.Date;

public class DataSensitive {
    private Integer id;

    private String datarule;

    private String datarulesign;

    private Integer datalevel;

    private Integer datacategory;

    private String datasource;

    private String distinguishtime;

    private Integer isactive;

    private String createdBy;

    private Date createdTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDatarule() {
        return datarule;
    }

    public void setDatarule(String datarule) {
        this.datarule = datarule == null ? null : datarule.trim();
    }

    public String getDatarulesign() {
        return datarulesign;
    }

    public void setDatarulesign(String datarulesign) {
        this.datarulesign = datarulesign == null ? null : datarulesign.trim();
    }

    public Integer getDatalevel() {
        return datalevel;
    }

    public void setDatalevel(Integer datalevel) {
        this.datalevel = datalevel;
    }

    public Integer getDatacategory() {
        return datacategory;
    }

    public void setDatacategory(Integer datacategory) {
        this.datacategory = datacategory;
    }

    public String getDatasource() {
        return datasource;
    }

    public void setDatasource(String datasource) {
        this.datasource = datasource == null ? null : datasource.trim();
    }

    public String getDistinguishtime() {
        return distinguishtime;
    }

    public void setDistinguishtime(String distinguishtime) {
        this.distinguishtime = distinguishtime == null ? null : distinguishtime.trim();
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