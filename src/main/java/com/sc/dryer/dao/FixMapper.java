package com.sc.dryer.dao;

import com.sc.dryer.pojo.Fix;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface FixMapper {
    int deleteByPrimaryKey(String id);

    int insert(Fix record);

    Fix selectByPrimaryKey(String id);

    List<Fix> selectAll();

    int updateByPrimaryKey(Fix record);
}