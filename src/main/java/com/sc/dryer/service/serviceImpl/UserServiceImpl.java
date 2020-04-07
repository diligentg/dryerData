package com.sc.dryer.service.serviceImpl;

import com.sc.dryer.dao.UsersMapper;


import com.sc.dryer.pojo.Users;
import com.sc.dryer.service.UserService;
import com.sc.dryer.utils.UpUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class UserServiceImpl implements UserService{
    @Resource
    private UsersMapper usersMapper;
    @Override
    public List<Users> selectAll() {
        List<Users> users = usersMapper.selectAll();
        return users;
    }
//    public List<Users> selectAll(Integer page, Integer limit) {
//        page=(page-1)*limit;
//        List<Users> users = usersMapper.selectAll(page,limit);
//        return users;
//    }

    @Override
    public String del(String[] id) {
        for (int i = 0; i <id.length ; i++) {
            Users user = usersMapper.selectByPrimaryKey(id[i]);
            if (user !=null){
                usersMapper.deleteByPrimaryKey(id[i]);
            }else {
                return "请刷新页面";
            }
        }
        return "success";
    }

    @Override
    public void up(MultipartFile file, HttpServletRequest request) {
        UpUtils.upfile(file,request);
    }

    @Override
    public void add(Users user) {
        String s = UUID.randomUUID().toString();
        user.setId(s);
        String pid = user.getPid();
        String p="img/"+pid;
        user.setPid(p);
        usersMapper.insert(user);
    }

    @Override
    public Users update(String id) {
        Users user = usersMapper.selectByPrimaryKey(id);
        return user;
    }

    @Override
    public Integer selectCount() {

        return usersMapper.selectCount();
    }

    @Override
    public void updateById(String value, String id, String field) {
//    public void updateById(Users user, String id,String[] field) {
//        usersMapper.updateById(user,id,field);
        usersMapper.updateById(value,id,field);
    }

    @Override
    public List<Users> selectByLike(String name,Integer page,Integer limit) {
        page=(page-1)*limit;
        List<Users> users=usersMapper.selectByLike(name,page,limit);
        return users;
    }

    @Override
    public Users selectByNp(String user, String password) {
        Users users=usersMapper.selectByNp(user,password);
        return users;
    }


}
