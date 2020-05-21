package com.fline.datasecurity.zj.dao.entity;

public class DataTypeFiled {
    private Integer id;

    private String datatype;

    private String datatypecode;

    private String datafields;

    private String datafieldname;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDatatype() {
        return datatype;
    }

    public void setDatatype(String datatype) {
        this.datatype = datatype == null ? null : datatype.trim();
    }

    public String getDatatypecode() {
        return datatypecode;
    }

    public void setDatatypecode(String datatypecode) {
        this.datatypecode = datatypecode == null ? null : datatypecode.trim();
    }

    public String getDatafields() {
        return datafields;
    }

    public void setDatafields(String datafields) {
        this.datafields = datafields == null ? null : datafields.trim();
    }

    public String getDatafieldname() {
        return datafieldname;
    }

    public void setDatafieldname(String datafieldname) {
        this.datafieldname = datafieldname == null ? null : datafieldname.trim();
    }
}