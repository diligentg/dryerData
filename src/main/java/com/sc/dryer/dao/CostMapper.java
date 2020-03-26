package com.sc.dryer.dao;

import com.sc.dryer.pojo.Cost;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface CostMapper {
    int deleteByPrimaryKey(String id);

    int insert(Cost record);

    Cost selectByPrimaryKey(String id);

    List<Cost> selectAll(@Param("page") Integer page,@Param("limit") Integer limit);

    int updateByPrimaryKey(Cost record);

    List<Cost> selectByLike(@Param("time")String time, @Param("page")Integer page,@Param("limit")Integer limit);

    Integer selectCount();
}