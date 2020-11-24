package com.huxiaofan.api;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Writer;

@WebServlet(name = "LogoutApi")
public class LogoutApi extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);

        //封装的http请求响应头
        httpUtils.httpUtil(request, response);

        //定义输出对象
        Writer o = response.getWriter();

        String token = request.getParameter("token");
        System.out.println("等待注销的 token 为：" + token);
        //移除session
        getServletContext().removeAttribute(token);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
