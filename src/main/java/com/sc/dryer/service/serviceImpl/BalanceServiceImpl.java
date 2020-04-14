package com.sc.dryer.service.serviceImpl;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.sc.dryer.dao.BalanceMapper;
import com.sc.dryer.pojo.AliDevPayConfig;
import com.sc.dryer.pojo.Balance;
import com.sc.dryer.service.BalanceService;
import com.sun.prism.impl.Disposer;
import org.aspectj.weaver.ast.Var;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class BalanceServiceImpl implements BalanceService{
    @Resource
    private BalanceMapper balanceMapper;
    @Override
    public List<Balance> selectAll() {
        List<Balance> balances = balanceMapper.selectAll();
        return balances;
    }

    @Override
    public void updateById(String value, String id, String field) {
        balanceMapper.updateById(value,id,field);
    }

    @Override
    public void rechargeById(String value, String id, String field) {
        balanceMapper.rechargeById(value,id,field);
    }
//充值支付
    @Override
    public String order(String paydto) {

        String orderString ="";
        String orderNo = UUID.randomUUID().toString();
        try {
            AliDevPayConfig aliDevPayConfig= new  AliDevPayConfig(); //实例化上面的那个配置类..
            //实例化客户端（参数：网关地址、商户appid、商户私钥、格式、编码、支付宝公钥、加密类型），为了取得预付订单信息
            AlipayClient alipayClient = new DefaultAlipayClient(AliDevPayConfig.URL, aliDevPayConfig.getAPPID(),
                    AliDevPayConfig.RSA_PRIVATE_KEY, AliDevPayConfig.FORMAT, AliDevPayConfig.CHARSET,
                    AliDevPayConfig.ALIPAY_PUBLIC_KEY, AliDevPayConfig.SIGNTYPE);
            //实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
            AlipayTradeAppPayRequest ali_request = new AlipayTradeAppPayRequest();
            //SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式
            AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
            //业务参数传入,可以传很多，参考API
            //model.setPassbackParams(URLEncoder.encode(request.getBody().toString())); //公用参数（附加数据）
            //对一笔交易的具体描述信息。如果是多种商品，请将商品描述字符串累加传给body。
            model.setBody("烘干机余额充值，通过余额支付使用烘干机");
            //商品名称
            model.setSubject("烘干机充值");
            //商户订单号(根据业务需求自己生成)
            model.setOutTradeNo(orderNo);
            //交易超时时间 这里的30m就是30分钟
            model.setTimeoutExpress("30m");
            //支付金额 后面保留2位小数点..不能超过2位
            model.setTotalAmount(paydto);
            //销售产品码（固定值） //这个不做多解释..看文档api接口参数解释
            model.setProductCode("QUICK_MSECURITY_PAY");
            ali_request.setBizModel(model);
            //异步回调地址（后台）//这里我在上面的aliPayConfig有讲..自己去看
            ali_request.setNotifyUrl(AliDevPayConfig.notify_url);
            System.out.println("====================异步通知的地址为：" + ali_request.getNotifyUrl());
            //同步回调地址（APP）同上
            ali_request.setReturnUrl(AliDevPayConfig.return_url);
            System.out.println("====================同步通知的地址为：" + ali_request.getReturnUrl());

            // 这里和普通的接口调用不同，使用的是sdkExecute
            //返回支付宝订单信息(预处理)
            AlipayTradeAppPayResponse alipayTradeAppPayResponse = alipayClient.sdkExecute(ali_request);
            //就是orderString 可以直接给APP请求，无需再做处理。
            orderString = "https://openapi.alipaydev.com/gateway.do?"+alipayTradeAppPayResponse.getBody();
            System.out.println(orderString);
        }catch (AlipayApiException e) {
            e.printStackTrace();
            System.out.println("与支付宝交互出错，未能生成订单，请检查代码！");
        }
        return orderString;
    }
//验签
    @Override
    public String aliNotify(Map<String, String> conversionParams) {
        System.out.println("=支付宝异步请求逻辑处理=");
        //签名验证(对支付宝返回的数据验证，确定是支付宝返回的)
        boolean signVerified = false;
        try {
            //调用SDK验证签名
            String alipayPublicKey = AliDevPayConfig.ALIPAY_PUBLIC_KEY;
            String charset = AliDevPayConfig.CHARSET;
            String signType = AliDevPayConfig.SIGNTYPE;

            signVerified = AlipaySignature.rsaCheckV1(conversionParams, alipayPublicKey, charset, signType);
            //对验签进行处理.
            if (signVerified) {
                System.out.println("+支付宝回调签名认证成功+");
                // 按照支付结果异步通知中的描述，对支付结果中的业务内容进行1\2\3\4二次校验，校验成功后在response中返回success，校验失败返回failure 支付宝官方建议校验的值（out_trade_no、total_amount、sellerId、app_id）
                this.check(conversionParams);
                //验签通过 获取交易状态
                String tradeStatus = conversionParams.get("trade_status");

                //只处理支付成功的订单: 修改交易表状态,支付成功
                //只有交易通知状态为TRADE_SUCCESS或TRADE_FINISHED时，支付宝才会认定为买家付款成功。
                if (tradeStatus.equals("TRADE_SUCCESS") ||tradeStatus.equals("TRADE_FINISHED")) {
                    //TODO 获取支付宝通知完成充值后续业务
                    //交易成功 获取商户订单号 你等等 我找找代码 好的

                    /**修改订单信息*/

                    /**余额到账*/
                    //这里就主要是做你们自己的业务需求了,修改一些表什么的..

                    return "success";
                } else {
                    return "fail";
                }
            } else{  //验签不通过
                System.out.println("++验签不通过 ！++");
                return "fail";
            }
        } catch (AlipayApiException e) {
            System.out.println("+++验签失败 ！+++");
        e.printStackTrace();
    }
        return "fail";
    }


    private void check(Map<String, String> conversionParams) throws AlipayApiException {
        AliDevPayConfig aliDevPayConfig = new AliDevPayConfig();
        String outTradeNo = conversionParams.get("out_trade_no");

        // 1、商户需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号， 这个查询单号 你自己写
        Disposer.Record record = balanceMapper.selectByOrderId(outTradeNo);
        if (record == null) {
            throw new AlipayApiException("out_trade_no错误"); // 这里做判断 下面的都一样。。 懂我的意思吧？
        }

        // 2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），

//        long total_amount = new BigDecimal(conversionParams.get("total_amount")).multiply(new BigDecimal(100)).longValue();
//        if (total_amount != record.getAmount()) {
//            throw new AlipayApiException("error total_amount");
//        }

        // 3、校验通知中的seller_id（或者seller_email)是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email），
        // 第三步可根据实际情况省略

        // 4、验证app_id是否为该商户本身。
        if (!conversionParams.get("app_id").equals(aliDevPayConfig.getAPPID())) {
            throw new AlipayApiException("app_id不一致");
        }
    }

}

