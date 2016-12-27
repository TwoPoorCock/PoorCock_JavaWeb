package com.poolm.pojo;

public class Dish {
    private Integer id;

    private String dishname;

    private Byte flagR;

    private Byte flagH;

    private Byte type;

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

    public Byte getFlagR() {
        return flagR;
    }

    public void setFlagR(Byte flagR) {
        this.flagR = flagR;
    }

    public Byte getFlagH() {
        return flagH;
    }

    public void setFlagH(Byte flagH) {
        this.flagH = flagH;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }
}