package com.sc.dryer.controller;

import com.sc.dryer.pojo.Result;
import com.sc.dryer.pojo.Users;
import com.sc.dryer.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/home")
public class HomeController {
    @Resource
    private UserService userService;
    @RequestMapping("/index")
    public String index() {
        return "login";
        }

@RequestMapping("/login")
@ResponseBody
    public Result login(String username, String password, HttpServletRequest request){
        Users users=userService.selectByNp(username,password);
        Result result = new Result();
        if (users!=null){
            request.getSession().setAttribute(username,password);
            return result;
        }else {
            result.setStatus("500");
            result.setMessage("error");
            return result;
        }
}






    @RequestMapping("/main")
    public String main() {
        return "main";
    }
}
