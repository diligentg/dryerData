package com.sc.dryer.pojo;

import java.util.List;

//封装返回的数据
public class Result {
    //状态码0表示成功
    private String status ="0";
    private String message="success";
    //数据长度
    private Integer total=0;
    //存放我们从后台拿到的数据列表
    private List item;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List getItem() {
        return item;
    }

    public void setItem(List item) {
        this.item = item;
    }
}
