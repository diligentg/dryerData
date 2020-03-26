package com.sc.dryer.dao;

import com.sc.dryer.pojo.Picture;
import java.util.List;

public interface PictureMapper {
    int deleteByPrimaryKey(String id);

    int insert(Picture record);

    Picture selectByPrimaryKey(String id);

    List<Picture> selectAll();

    int updateByPrimaryKey(Picture record);
}