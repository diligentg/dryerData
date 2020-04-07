package com.sc.dryer.pojo;

import java.io.Serializable;

public class Fix implements Serializable {
    private String id;

    private String dormi;

    private String num;

    private String fname;

    private String type;

    private String fixmsg;

    private String fiximg;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getDormi() {
        return dormi;
    }

    public void setDormi(String dormi) {
        this.dormi = dormi == null ? null : dormi.trim();
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num == null ? null : num.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }




    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getFiximg() {
        return fiximg;
    }

    public void setFiximg(String fiximg) {
        this.fiximg = fiximg;
    }

    public String getFixmsg() {
        return fixmsg;
    }

    public void setFixmsg(String fixmsg) {
        this.fixmsg = fixmsg;
    }
}