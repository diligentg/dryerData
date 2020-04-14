package com.sc.dryer.dao;

import com.sc.dryer.pojo.Balance;
import com.sun.prism.impl.Disposer;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface BalanceMapper {
    int deleteByPrimaryKey(String id);

    int insert(Balance record);

    Balance selectByPrimaryKey(String id);

    List<Balance> selectAll();

    int updateByPrimaryKey(Balance record);

    void updateById(@Param("value")String value, @Param("id") String id, @Param("field") String field);
    void rechargeById(@Param("value")String value, @Param("id") String id, @Param("field") String field);

    Disposer.Record selectByOrderId(String outTradeNo);
}