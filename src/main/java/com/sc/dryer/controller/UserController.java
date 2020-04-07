package com.sc.dryer.controller;

import com.sc.dryer.pojo.Result;
import com.sc.dryer.pojo.Users;
import com.sc.dryer.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {
    @RequestMapping("/index")
    public String index(){
        return "user";
    }
    @RequestMapping("/i")
    public String i(){
        return "i";
    }
    @Resource
    private UserService userService;
    @RequestMapping("/selectAll")
    @ResponseBody
//    public Result selectAll(Integer page,Integer limit){
    public Result selectAll(){
        //从数据库查询5条数据
        //select*from user limit 0,5
//        List<Users> users = userService.selectAll(page,limit);
        List<Users> users = userService.selectAll();
        Result result = new Result();
        if (users!=null){
            Integer total=userService.selectCount();
            result.setTotal(total);
            result.setItem(users);
            return result;
        }else {
            result.setStatus("500");
            result.setMessage("error");
            return result;
        }
    }
    @RequestMapping("/del")
    @ResponseBody
    public Result del(String[] id){
        Result result = new Result();
        try {
            String del = userService.del(id);
            if (del.equals("success")){
                return result;
            }else{
                result.setMessage(del);
                return result;
            }
        }catch (Exception e){
                e.printStackTrace();
                result.setMessage("删除失败");
                return result;
        }
    }
    @RequestMapping("/up")
    @ResponseBody
    public Result up(MultipartFile file,HttpServletRequest request){
        Result result = new Result();
        try {
            userService.up(file,request);
            String s = file.getOriginalFilename();
            result.setMessage(s);
            return result;
        }catch (Exception e){
            e.printStackTrace();
            result.setMessage("文件操作异常");
            return result;
        }
    }
    @RequestMapping("/add")
    @ResponseBody
    public  Result add(Users user){
        Result result = new Result();
        try {
            userService.add(user);
            return result;
        }catch (Exception e){
            e.printStackTrace();
            result.setMessage("数据添加异常");
            return result;
        }
    }
    @RequestMapping("/update")
    @ResponseBody
    public Result update(String value,String id,String field){
//    public Result update(Users user,String id,String[] field){
        Result result = new Result();
        try {
//            userService.updateById(user,id,field);
            userService.updateById(value,id,field);
            return result;
        }catch (Exception e){
            e.printStackTrace();
            result.setMessage("修改操作异常");
            return result;
        }
    }
    //根据条件查询的方法
    @RequestMapping("/selectByLike")
    @ResponseBody
    public Result selectByLike(String name,Integer page,Integer limit){
        Result result = new Result();
        List<Users> users=userService.selectByLike(name,page,limit);
        result.setItem(users);
        result.setTotal(users.size());
        return result;
    }
}
