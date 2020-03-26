package com.sc.dryer.dao;

import com.sc.dryer.pojo.Announce;
import java.util.List;

public interface AnnounceMapper {
    int deleteByPrimaryKey(String id);

    int insert(Announce record);

    Announce selectByPrimaryKey(String id);

    List<Announce> selectAll();

    int updateByPrimaryKey(Announce record);
}