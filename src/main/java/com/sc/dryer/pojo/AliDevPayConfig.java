package com.sc.dryer.pojo;


public class AliDevPayConfig {

    /**
     * 1.商户appid
     */
    public String APPID = "2016102300743602";

    /**
     * 私钥 pkcs8格式的
     */
    public static String RSA_PRIVATE_KEY = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCmlHKrCk4DR0qugBINCKSNWqBtvuFtf6BM7s8mHu0L4cvACAYSw+P6pSiK+wAoTCfbNC3Oy/hDosIwvhO+O+l2ZLVqGgDHLBg8rsMEdf3Po4ZGaTvt7jNyYGHvh7j5T+ulT+tkpVRaFHss5UFPFLHEY/bLG/vHYHeyMerTh+m3xpv6lJjKTCPLpm9lCUxIZwdBNu1dpwBl7MuSZGyWD13lMnbtytf/R7px3O/7SR9BWCC6XI14tOAdU56rYzX9iZ67PSiIQny7PIE5XtI6jcKom1bw3s868yRkqWVCHZuHz25TgYStBaZ0/8u9Ak6AfqcO/1Ago9GznJ0qXzRaiQ7XAgMBAAECggEBAIDMjTEEwbc1O2BbdBmR0jHdnefdTE4u/VIe4Bp4lQ634qOzUIbX4gGeDBAR7Vg7MiICYVzhRy98vCuV/Iv9pRE+4yqLuohF5uOcuPtNBBe1N5Bfs8/PewX30iGzzmkuy2RBvB9Q2jzgUzEufeXYeW8BYARBuda6FzK39/rgvuyxpqEIBEQV4fRVeEmeKaS5Vjh6ANSD5iieJoKlwoSsYIyFDN9MEcPbdtJKzU/dZAS1cbHYkFki+OYc6Sfdpg9O0Tq3qJAGAEK4X0euC6aZO1Ns+ywEr/7dnrgmUxCON/CEuEk/TgMYdtYDv3QebFZnZHmOQ/cndUtKrfXuIL/M0aECgYEA991xBxO71lMpe1G+XRnE/o898hkofAQZh0h9GuIQb/zUARCY/MUeJ1MJAgiaDyt2T452gfaw1U+WY7FjRPWnnUuYZ7RSQ3uOOixsuHfFxpz1nDN1utbVbEHMYAHAtb5MuU+Q+eyoQoNuKMgf16zw7zztnEiVpVauddpH4/uKw0cCgYEArAwMwLtJW3A3QL0wb2w2PV5I+nd6+OYa8u1f9ceflPFHFn7k+DspWqFT9ZK4/E0kwlPF0oNByFLvpeIQe244yUFShTnaxXqAyMaLYGuA4MbKyGkdDp0lH1/+Cg/ASHloZGflP90VQNRuWdwgGe+mLOakZ6heDaIJZxyKPldBf/ECgYEAqvXm5t26mnqjyvY32hlTIc2FCX5GyTs+rGvYNnmkosl9JPrgur/vK2p/dRKfyETPBtKZdMzFbWvGrB6G/OJ/aOPiS+05LGiFUiSgHKAoHvTWB5YK5wMVE+VSZQnwdKq8WrjBo1CTQcVLbv+Rzv3u9OROc5Qw+2iNTqZvHZW7QTMCgYBIEcAh2BJxoD80+ktY0W6jJGTTrssvikyNqIwDC/04Sh3qOXu21CmNknj2LOEap0IXuTnr5hnfTYaf6WPYIKLfA2MK9eJDyP47nJfz6vSQtBVsAEQww/kx/GcRVenJMjpdnPv2Vak6gAI92C0GUFI/CtqhpXDIqzDfJaBvXuPMMQKBgDq6PyI21OqFBVV8Sm3FCF6MSno7dU6Iwl6yauhgTdHjkiz8YZEHJxd8BOEA0DpAwDvZpJVbWqxgM1p/sjeHiifd4US2mYHHZgZTNjsc7DBnN3raa2dna7xX/qT7a/qy8Ne+CjuIqNAsXaB0j0V8mDh9Ui7OF/E4NOV7zcjolO4R";

    /**
     * 3.支付宝公钥
     */
    public static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAtayR2ZYutsaMKM8DivwN15Jf9RpzaLehMDk1RpwVTWlamBs+lxBC+rixi0casAxB0NSUtgykZt7WYXKhQ76Dui16qqBsOBu8uh2/L6+iJAsAn7DbtQrlsXkPs+0lmd59jTlVLukMaF6h7+bUhuMtz1jgZ0pC7ua9YOv+MHoWDwXL7sd065EbAKDmBB6FCQwQ7BWtn+0ykdXIbQDqOwJfexrtmGCRoxmWxWAeNgHa8eEMa4BRLv2rNCGWBPA+eHT9/XgE0p3s+eJ0WrWGJNVET/vjP4Nc6Ptd31mLeyPGxEmr2BEnUfv27G156SXI7E1wDAOXuyt+hlMAW1FTaEQUZQIDAQAB";

    /**
     * 4.服务器异步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
     */
//这个着重讲一下..支付完成后,支付宝会通过这个url请求到你的服务端..
//这个url一定是要公网可以访问才行...如果需要测试的话..我后面有讲到..
//这里你可以先写你本地项目的url 例如:localhost:8080/项目名/访问路径 http://3lv0842256.zicp.vip/dryer/balances/aliNotify
    public static String notify_url = "http://3lv0842256.zicp.vip/dryer/balances/aliNotify";

    /**
     * 5.页面跳转同步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问 商户可以自定义同步跳转地址
     */
    //这里同上..不做详细说明了..
    public static String return_url = "http://localhost:8081/#/balance";

    /**
     * 正式环境支付宝网关，如果是沙箱环境需更改成https://openapi.alipaydev.com/gateway.do
     */
    public static String URL = "https://openapi.alipaydev.com/gateway.do";

    /**
     * 7.编码
     */
    public static String CHARSET = "UTF-8";

    /**
     * 私钥 pkcs8格式的
     */
    // 8.返回格式
    public static String FORMAT = "json";

    /**
     * //签名方式 加密类型
     */
    public static String SIGNTYPE = "RSA2";

    public String getAPPID() {
        return APPID;
    }
}
