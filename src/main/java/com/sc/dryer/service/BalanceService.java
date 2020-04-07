package com.sc.dryer.service;

import com.sc.dryer.pojo.Balance;

import java.util.List;

public interface BalanceService {

    List<Balance> selectAll();

    void updateById(String value, String id, String field);
}
