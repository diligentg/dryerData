package com.sc.dryer.controller;

import com.sc.dryer.pojo.Cost;
import com.sc.dryer.pojo.Result;
import com.sc.dryer.service.CostService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/costs")
public class CostController {
    @RequestMapping("/index")
    public String index(){
        return "cost";
    }
    @Resource
    private CostService costService;
    @RequestMapping("/selectAll")
    @ResponseBody
    public Result selectAll(){
        List<Cost> costs = costService.selectAll();
        Result result = new Result();
        if (costs!=null){
            Integer total=costService.selectCount();
            result.setTotal(total);
            result.setItem(costs);
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
            String del = costService.del(id);
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
    public Result up(MultipartFile file, HttpServletRequest request){
        Result result = new Result();
        try {
            costService.up(file,request);
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
    public  Result add(Cost cost){
        Result result = new Result();
        try {
            costService.add(cost);
            return result;
        }catch (Exception e){
            e.printStackTrace();
            result.setMessage("数据添加异常");
            return result;
        }
    }
    //根据条件查询的方法
    @RequestMapping("/selectByLike")
    @ResponseBody
    public Result selectByLike(String time,Integer page,Integer limit){
        Result result = new Result();
        List<Cost> costs=costService.selectByLike(time,page,limit);
        result.setItem(costs);
        result.setTotal(costs.size());
        return result;

    }
}
