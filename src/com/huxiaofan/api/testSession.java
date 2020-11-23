package com.huxiaofan.api;
//session对象测试类
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Writer;
import java.util.Enumeration;
import java.util.HashMap;

@WebServlet(name = "testSession")
public class testSession extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //封装的http请求响应头
        httpUtils.httpUtil(request, response);

        Writer o = response.getWriter();

        String FileName="";
        HttpSession session=request.getSession();//获取session
        Enumeration enumerationA =session.getAttributeNames();//获取session中所有的键值对

        //方法二：通过for循环进行遍历
        while(enumerationA.hasMoreElements()){
            // 获取session的属性名称
            String name = enumerationA.nextElement().toString();
            // 根据键值取session中的值
            Object value = session.getAttribute(name);
            // 打印结果
            System.out.println("----------" + name + "------------" + value );
        }

        //通过传入参数取得指定session
        String token = request.getParameter("token");
        if (token!=null){
            HttpSession hs = (HttpSession) getServletContext().getAttribute(token);

            Enumeration enumerationB =hs.getAttributeNames();//获取session中所有的键值对

            //方法二：通过for循环进行遍历
            while(enumerationB.hasMoreElements()){
                // 获取session的属性名称
                String name = enumerationB.nextElement().toString();
                // 根据键值取session中的值
                Object value = hs.getAttribute(name);
                // 打印结果
                System.out.println("----------" + name + "------------" + value );
            }
        }

    }
}
