package com.sc.dryer.pojo;

import java.io.Serializable;

public class Cost implements Serializable {
    private String id;

    private String usetype;

    private String consumetime;

    private String location;

    private String pay;

    private String uname;

    private String consumestatus;

    private String way;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    public String getPay() {
        return pay;
    }

    public void setPay(String pay) {
        this.pay = pay == null ? null : pay.trim();
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname == null ? null : uname.trim();
    }

    public String getWay() {
        return way;
    }

    public void setWay(String way) {
        this.way = way;
    }

    public String getConsumestatus() {
        return consumestatus;
    }

    public void setConsumestatus(String consumestatus) {
        this.consumestatus = consumestatus;
    }

    public String getConsumetime() {
        return consumetime;
    }

    public void setConsumetime(String consumetime) {
        this.consumetime = consumetime;
    }

    public String getUsetype() {
        return usetype;
    }

    public void setUsetype(String usetype) {
        this.usetype = usetype;
    }
}