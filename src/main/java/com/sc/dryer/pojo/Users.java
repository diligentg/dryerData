package com.sc.dryer.pojo;

import java.io.Serializable;

public class Users implements Serializable {
    private String id;

    private String user;

    private String password;

    private String name;

    private String dormi;

    private String sex;

    private String pid;

    private String classmsg;

    private String school;

    private String phone;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user == null ? null : user.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDormi() {
        return dormi;
    }

    public void setDormi(String dormi) {
        this.dormi = dormi == null ? null : dormi.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid == null ? null : pid.trim();
    }

    public String getClassmsg() {
        return classmsg;
    }

    public void setClassmsg(String classmsg) {
        this.classmsg = classmsg;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}