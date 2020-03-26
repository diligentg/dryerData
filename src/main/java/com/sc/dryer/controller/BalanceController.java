package com.sc.dryer.controller;

import com.sc.dryer.pojo.Balance;
import com.sc.dryer.pojo.Result;
import com.sc.dryer.service.BalanceService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/balances")
public class BalanceController {
    @RequestMapping("/index")
    public String index(){
        return "balance";
    }
    @Resource
    private BalanceService balanceService;
    @RequestMapping("/selectAll")
    @ResponseBody
    public Result selectAll(){
        List<Balance> balances=balanceService.selectAll();
        Result result = new Result();
        if (balances!=null){
            result.setItem(balances);
            return result;
        }else {
            result.setStatus("500");
            result.setMessage("error");
            return result;
        }
    }
}