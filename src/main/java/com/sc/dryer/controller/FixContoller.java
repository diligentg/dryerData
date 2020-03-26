package com.sc.dryer.controller;

import com.sc.dryer.pojo.Fix;
import com.sc.dryer.pojo.Result;

import com.sc.dryer.service.FixService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/fixs")
public class FixContoller {
    @RequestMapping("/index")
    public String index(){
        return "fix";
    }
    @Resource
    private FixService fixService;
    @RequestMapping("/selectAll")
    @ResponseBody
    public Result selectAll(){
        List<Fix> fixs = fixService.selectAll();
        Result result = new Result();
        if (fixs!=null){
            result.setItem(fixs);
            return result;
        }else {
            result.setStatus("500");
            result.setMessage("error");
            return result;
        }
    }

    @RequestMapping("/up")
    @ResponseBody
    public Result up(MultipartFile file, HttpServletRequest request){
        Result result = new Result();
        try {
            fixService.up(file,request);
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
    public  Result add(Fix fix){
        Result result = new Result();
        try {
            fixService.add(fix);
            return result;
        }catch (Exception e){
            e.printStackTrace();
            result.setMessage("数据添加异常");
            return result;
        }
    }
    @RequestMapping("/del")
    @ResponseBody
    public Result del(String[] id){
        Result result = new Result();
        try {
            String del = fixService.del(id);
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
}
