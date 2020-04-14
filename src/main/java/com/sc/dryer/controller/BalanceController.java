package com.sc.dryer.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradePrecreateRequest;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.sc.dryer.pojo.Balance;
import com.sc.dryer.pojo.Result;
import com.sc.dryer.service.BalanceService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.*;

@Controller
@RequestMapping("/balances")
public class BalanceController {
    @RequestMapping("/index")
    public String index() {
        return "balance";
    }

    @Resource
    private BalanceService balanceService;

    @RequestMapping("/selectAll")
    @ResponseBody
    public Result selectAll() {
        List<Balance> balances = balanceService.selectAll();
        Result result = new Result();
        if (balances != null) {
            result.setItem(balances);
            return result;
        } else {
            result.setStatus("500");
            result.setMessage("error");
            return result;
        }
    }

    @RequestMapping("/update")
    @ResponseBody
    public Result update(String value, String id, String field) {
        Result result = new Result();
        try {
            balanceService.updateById(value, id, field);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            result.setMessage("修改操作异常");
            return result;
        }
    }

    @RequestMapping("/recharge")
    @ResponseBody
    public Result recharge(String value, String id, String field) {
        Result result = new Result();
        try {
            balanceService.rechargeById(value, id, field);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            result.setMessage("修改操作异常");
            return result;
        }
    }

    @RequestMapping("/alipay1")
    @ResponseBody
    public String charge(String paydto) {
        //payDto就是你前端传过来的值,比如充值金额是多少 用户id是多少..
        //这个实体类根据你们自己的业务需求去写就好
        try {
            String orderString=balanceService.order(paydto);
            return orderString;
        } catch (Exception e){
            e.printStackTrace();
            return "修改操作异常";
        }
    }

    @RequestMapping("/aliNotify")
    @ResponseBody
    public String aliNotify(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("=支付宝异步返回支付结果开始");
        //1.从支付宝回调的request域中取值
        //获取支付宝返回的参数集合 再支付一次  配拦截器了没？
        Map<String, String[]> aliParams = request.getParameterMap();
        //用以存放转化后的参数集合
        Map<String, String> conversionParams = new HashMap<String, String>();
        for (Iterator<String> iter = aliParams.keySet().iterator(); iter.hasNext();) {
            String key = iter.next();
            String[] values = aliParams.get(key);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            // 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
            //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "UTF-8");
            conversionParams.put(key, valueStr);
        }
        System.out.println("==返回参数集合："+conversionParams);
        String status=balanceService.aliNotify(conversionParams);
        return status;
    }

    @RequestMapping("/alipay")
    @ResponseBody
    public String alipay(HttpServletRequest request, HttpServletResponse response, String count)
            throws IOException {
        AlipayClient alipayClient = new
                DefaultAlipayClient("https://openapi.alipaydev.com/gateway.do",
                "2016102300743602",
                "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCYq5ESRB5Kv/Gba5Q6VJN54YQjOirxJfvhjsnvnWTiOPYNhvgv9hfyJmRtSePKwOWbbosHSwvkBHV88GbUx58bHgY/6ISbtnvpm3jegQ+qoFfXpGEkujlFgfKQ5RJtkygpmIcGGNGDdTBhbOBCh2XTbx1irNAspuu88IDN0lVWwR0Av4u8sw8kTC9lbHdTM5wmNLRFe/HAGzOa5tgUOSzsSMIl+zJ8+vhg9aWUW/DeLWl4+cadubUkZO+j/MnLDR3hLTQ62RCNbxx61Hp+1KJF33M9SZ9OoEP/zQub0902J9RgBr4KQChuS8yJtCayVQ/qZYxx8PoLAt138uk2vybvAgMBAAECggEADzDbEX83qGZq6vUMXbV2qyT2We76wN3vryTfEPmPRTc3vGbWoScSgOSzD3DPuqfw8MNSEBq2hyyuaA0fnkJyoWceZhODxl6k+7ydjT44SdkEhI91nwrske75LNOuPrrqvyDPJpsnlbX+Z2NG+TexuRUQTnY1jIvbXwRrPI+WMDiFizJUqLS/GpzbDAYKOgDxOsaEqzbNmmcRh6sxNssAVJ6KaNKktb0okFzm4TNTRx4UkVV1ZkldR51SLbdFMtLtWdNQpFFQxTSDWtko1a5+f8nN1LhwsEDYa1KGDi7BgJWU1rxGJiIzW84xX2l9lJm71zKqHRz5J536x0AYbEC8mQKBgQDHdW/KjUUyFbaHJRh+Jzci9WsA1JNLNbqE0lZrmx7gngBlQBTqpCd+yMAlvVx0J615U7tFiwkwM7ypDe6RzzDHLvJflMaIF2dGVCMWLi+MV0wTKz0y3KIRCR5ElhqlPLvgrlrl4/OHQWQ9cZFXLQziItsMf1ptC7+Tle0SjVzXHQKBgQDD8riyRBAViOnW2uHrbgzpywCa6lqVdJAv864bchEvd1nzAWrLRFu6ExtYG2CDfLK//8qMEz+XeHc2LMmtjqP/M0iDZke5UQnlLDVFbOXGgJpMsiGRR/dXgYosog4vUwExgzmRyJ3Vqa7xNwmHZofge8r88/957xVXavV7GU08ewKBgQCNhHpL6Y316kf2fVByeZp6OEyyayES1M1A3ggpEweGcTRwolX04YGQSBlk7YUCeQBxPAJ8Zhif1aV/AP6K/8lpGZsoS+PMvWYuFRZL73sXBP1aAGzMgJSQp7qNCT9gi8X1tV+TCvhHuBLKMaDgjHs+m2J17IoZIqPLsC1j417C1QKBgQC1zAhYPJW/pRtvX/yjdaM9dj7FfBE3AMKV+rvacuZG57DoqLPwiyefaALCqAPPem5NfIDrnSAu5HGWLc+f2uuE5ousfcSlxu4rVezG0NYOaVq/NbW3GNH+ugz8hA4tEhHVYi6Td1IY3imVWr7YhOQj/jbCoY6yDoP6ax3+a+cg6QKBgBFeJcbZ2+nVwaLIdhYBzzZjf/VZhJ0brPb8r699UU3zWPKWCjNZ79S9FHvlk5FWy2xG+psDz3SG/6ug1inR7GlGihpmkZFYZsMGSI7pRI8Q/1rl0UvvEIm9TvXOFyuezG7gojZnliNhn5buOn5J8Mpyqrp7Ck7pYIEB9jklWhaa",
                "json", "utf-8",
                "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAtayR2ZYutsaMKM8DivwN15Jf9RpzaLehMDk1RpwVTWlamBs+lxBC+rixi0casAxB0NSUtgykZt7WYXKhQ76Dui16qqBsOBu8uh2/L6+iJAsAn7DbtQrlsXkPs+0lmd59jTlVLukMaF6h7+bUhuMtz1jgZ0pC7ua9YOv+MHoWDwXL7sd065EbAKDmBB6FCQwQ7BWtn+0ykdXIbQDqOwJfexrtmGCRoxmWxWAeNgHa8eEMa4BRLv2rNCGWBPA+eHT9/XgE0p3s+eJ0WrWGJNVET/vjP4Nc6Ptd31mLeyPGxEmr2BEnUfv27G156SXI7E1wDAOXuyt+hlMAW1FTaEQUZQIDAQAB",
                "RSA2");
            String ding = UUID.randomUUID().toString();
            String out_trade_no = ding;
            out_trade_no = URLDecoder.decode(out_trade_no, "UTF-8");
            String total_amount = count;
            total_amount = URLDecoder.decode(total_amount, "UTF-8");
            String subject = "我的钱包充值";
            subject = URLDecoder.decode(subject, "UTF-8");
            String body = "充值到余额，可进行消费";
            body = URLDecoder.decode(body, "UTF-8");

        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();//创建API对应的request
//        AlipayTradeWapPayRequest alipayRequest = new AlipayTradeWapPayRequest();//创建API对应的request
        alipayRequest.setReturnUrl("http://192.168.1.36:8081/#/balance");
        alipayRequest.setNotifyUrl("http://3lv0842256.zicp.vip/dryer/balances/recharge");

        alipayRequest.setBizContent("{" +
                "    \"out_trade_no\":\"" + out_trade_no + "\"," +
                "    \"product_code\":\"FAST_INSTANT_TRADE_PAY\"," +
                "    \"total_amount\":" + total_amount + "," +
                "    \"subject\":\"" + subject + "\"," +
                "    \"body\":\"" + body + "\"" +
                "    }" +
                "  }");//填充业务参数
        String form = "";
        try {
            form = alipayClient.pageExecute(alipayRequest,"GET").getBody(); //调用SDK生成表单
//            System.out.println(form);
            return form;
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().println(form);//直接将完整的表单html输出到页面
        response.getWriter().close();
        return "success";
    }


}