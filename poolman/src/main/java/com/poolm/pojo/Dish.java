package com.poolm.pojo;

public class Dish {
    private Integer id;

    private String dishname;

    private String flagR;

    private String flagH;

    private Integer type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDishname() {
        return dishname;
    }

    public void setDishname(String dishname) {
        this.dishname = dishname == null ? null : dishname.trim();
    }

    public String getFlagR() {
        return flagR;
    }

    public void setFlagR(String flagR) {
        this.flagR = flagR == null ? null : flagR.trim();
    }

    public String getFlagH() {
        return flagH;
    }

    public void setFlagH(String flagH) {
        this.flagH = flagH == null ? null : flagH.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}