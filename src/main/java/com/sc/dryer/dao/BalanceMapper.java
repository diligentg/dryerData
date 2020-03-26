package com.sc.dryer.dao;

import com.sc.dryer.pojo.Balance;
import java.util.List;

public interface BalanceMapper {
    int deleteByPrimaryKey(String id);

    int insert(Balance record);

    Balance selectByPrimaryKey(String id);

    List<Balance> selectAll();

    int updateByPrimaryKey(Balance record);
}