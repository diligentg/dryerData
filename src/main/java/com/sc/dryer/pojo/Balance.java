package com.sc.dryer.pojo;

import java.io.Serializable;

public class Balance implements Serializable {
    private String id;

    private String uid;

    private String remain;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    public String getRemain() {
        return remain;
    }

    public void setRemain(String remain) {
        this.remain = remain == null ? null : remain.trim();
    }
}