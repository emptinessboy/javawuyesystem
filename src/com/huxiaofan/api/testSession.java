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
        Object name=session.getAttribute(request.getParameter("session"));
        System.out.println("通过参数匹配到的session为：" + name);
        Enumeration enumeration =session.getAttributeNames();//获取session中所有的键值对

        //方法二：通过for循环进行遍历
        while(enumeration.hasMoreElements()){
            String AddFileName=enumeration.nextElement().toString();//获取session中的键值

            HashMap<String, String> value= (HashMap<String, String>) session.getAttribute(AddFileName);//根据键值取出session中的值
            System.out.println("遍历得到的session内容：" + value.get("eid"));
            System.out.println("遍历得到的session内容：" + value.get("ename"));
            System.out.println("遍历得到的session内容：" + value.get("isadmin"));
//            String value=(String)session.getAttribute(AddFileName);//根据键值取出session中的值
//            System.out.println("遍历得到的session内容：" + value);

            //String FileName= (String)session.getAttribute("AddFileName");
        }


    }
}
