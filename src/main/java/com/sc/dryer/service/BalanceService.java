package com.sc.dryer.service;

import com.sc.dryer.pojo.Balance;

import java.util.List;
import java.util.Map;

public interface BalanceService {

    List<Balance> selectAll();

    void updateById(String value, String id, String field);

    void rechargeById(String value,String id, String field);


    String order(String paydto);

    String aliNotify(Map<String, String> conversionParams);
}
