package com.sc.dryer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class AlipayConfig {

    // 合作身份者ID，签约账号，以2088开头由16位纯数字组成的字符串，查看地址：https://openhome.alipay.com/platform/keyManage.htm?keyType=partner
    public static String partner = "***";

    // 商户的私钥,需要PKCS8格式，RSA公私钥生成：https://doc.open.alipay.com/doc2/detail.htm?spm=a219a.7629140.0.0.nBDxfy&treeId=58&articleId=103242&docType=1
    public static String private_key="***" ;

    // 支付宝的公钥，查看地址：https://openhome.alipay.com/platform/keyManage.htm?keyType=partner
    public static String alipay_public_key = "***";

    // 调试用，创建TXT日志文件夹路径，见AlipayCore.java类中的logResult(String sWord)打印方法。
    public static String server_uri = "https://openapi.alipay.com/gateway.do";

    // 字符编码格式 目前支持 gbk 或 utf-8
    public static String input_charset = "utf-8";

// 接收通知的接口名
// public static String service = "http://60.***.***.00/callbacks.do";
// public static String service = "mobile.securitypay.pay";

    // APPID
    public static String app_id = "***";

    // ↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
//    @RequestMapping(value = "/mhkyzfy", produces = "text/html;charset=UTF-8", method = { RequestMethod.GET })
//    public @ResponseBody
//    String get_Yhming(HttpServletRequest req, HttpServletResponse resp) {
//// 实例化客户端
//        AlipayClient alipayClient = new DefaultAlipayClient(
//                "https://openapi.alipay.com/gateway.do", app_id, private_key,
//                "json", input_charset, alipay_public_key, "RSA2");
//// 实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
//        AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
//// SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
//        AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
//
//// 对一笔交易的具体描述信息
//        model.setBody("我是测试数据");
//// 商品的标题/交易标题/
//        model.setSubject("App支付测试Java");
//// 商户网站唯一订单号
//        model.setOutTradeNo("001");
//// 该笔订单允许的最晚付款时间，逾期将关闭交易。
//        model.setTimeoutExpress("30m");
//// 订单总金额，单位为元
//        model.setTotalAmount("0.01");
//// 销售产品码，商家和支付宝签约的产品码，为固定值QUICK_MSECURITY_PAY
//        model.setProductCode("QUICK_MSECURITY_PAY");
//
//
//        request.setBizModel(model);
//        request.setNotifyUrl("商户外网可以访问的异步地址");
//        String orderInfo = null;
//        try {
//// 这里和普通的接口调用不同，使用的是sdkExecute
//            AlipayTradeAppPayResponse response = alipayClient
//                    .sdkExecute(request);
//            orderInfo = response.getBody();
//            System.out.println(response.getBody());// 就是orderString
//// 可以直接给客户端请求，无需再做处理。
//        } catch (AlipayApiException e) {
//            e.printStackTrace();
//        }
//        return orderInfo;
//    }
}
