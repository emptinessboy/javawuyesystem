package com.huxiaofan.api;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Writer;
import java.util.Enumeration;

@WebServlet(name = "LogoutApi")
public class LogoutApi extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //封装的http请求响应头
        httpUtils.httpUtil(request, response);

        //定义输出对象
        Writer o = response.getWriter();

        String token = request.getParameter("token");

        if (token!=null) {
            HttpSession hs = (HttpSession) getServletContext().getAttribute(token);
            if (hs==null){
                System.out.println("令牌校验失败！");
                o.write("令牌校验失败！");
                return;
            }else {
                //获取session中所有的键值对
                Enumeration enumerationB =hs.getAttributeNames();
                //方法二：通过for循环进行遍历
                while(enumerationB.hasMoreElements()){
                    // 获取session的属性名称
                    String name = enumerationB.nextElement().toString();
                    // 根据键值取session中的值
                    Object value = hs.getAttribute(name);
                    // 打印结果
                    System.out.println("----------" + name + "------------" + value );
                }
                //删除 session
                System.out.println("等待注销的 token 为：" + token);
                //移除session
                getServletContext().removeAttribute(token);
                response.setStatus(200);
                o.write("注销成功！");
            }
        }else {
            System.out.println("服务器找不到会话信息！");
            o.write("服务器找不到会话信息！");
            return;
        }

    }
}
