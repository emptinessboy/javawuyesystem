package com.huxiaofan.api;
//封装的http请求响应头
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

public class httpUtils {
    public static void httpUtil(HttpServletRequest request, HttpServletResponse response){
        try {
            request.setCharacterEncoding("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        response.setContentType("application/json; charset=utf-8");
        //允许跨域请求
        //response.setHeader("Access-Control-Allow-Origin", "*"); //  这里最好明确的写允许的域名
        response.setHeader("Access-Control-Allow-Origin", "https://wyglxt.app.huxiaofan.com"); //  这里最好明确的写允许的域名
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:8081"); //  这里最好明确的写允许的域名
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT, HEAD");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type,Access-Token,Authorization,ybg,token");
        response.setHeader("Access-Control-Allow-Credentials", "true");
    }
}
