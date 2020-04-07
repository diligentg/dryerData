package com.sc.dryer.dao;

import com.sc.dryer.pojo.Users;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface UsersMapper {
    int deleteByPrimaryKey(String id);

    int insert(Users record);

    Users selectByPrimaryKey(String id);

    List<Users> selectAll();
//    List<Users> selectAll(@Param("page") Integer page,@Param("limit") Integer limit);

    int updateByPrimaryKey(Users record);

    Integer selectCount();
    //传多个参数用@Param("value")
    void updateById(@Param("value")String value,@Param("id") String id,@Param("field") String field);
//    void updateById(@Param("user")Users value,@Param("id") String id,@Param("field") String[] field);

    List<Users> selectByLike(@Param("name")String name,@Param("page")Integer page,@Param("limit")Integer limit);


    Users selectByNp(@Param("user")String user, @Param("password")String password);
}