package com.sc.dryer.service;

import com.sc.dryer.pojo.Users;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletRequest;
import java.util.List;
@Component
public interface UserService {
    //查询用户信息
//    List<Users> selectAll(Integer page, Integer limit);
    List<Users> selectAll();

    String del(String[] id);

    void up(MultipartFile file, HttpServletRequest request);

    void add(Users user);


    Users update(String id);
    //统计数据库中的数据条数
    Integer selectCount();
    //修改某个字段的值
//    void updateById(Users user, String id,String[] field);
    void updateById(String value, String id, String field);

    List<Users> selectByLike(String name,Integer page,Integer limit);

    Users selectByNp(String user, String password);


    //模糊查询

}
