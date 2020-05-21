package com.fline.datasecurity.zj.dao.entity;

public class DesensitiveMethod {
    private Integer id;

    private String name;

    private String useexplain;

    private String defaultchoice;

    private String recommend;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getUseexplain() {
        return useexplain;
    }

    public void setUseexplain(String useexplain) {
        this.useexplain = useexplain == null ? null : useexplain.trim();
    }

    public String getDefaultchoice() {
        return defaultchoice;
    }

    public void setDefaultchoice(String defaultchoice) {
        this.defaultchoice = defaultchoice == null ? null : defaultchoice.trim();
    }

    public String getRecommend() {
        return recommend;
    }

    public void setRecommend(String recommend) {
        this.recommend = recommend == null ? null : recommend.trim();
    }
}