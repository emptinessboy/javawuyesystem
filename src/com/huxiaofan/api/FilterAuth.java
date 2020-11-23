package com.huxiaofan.api;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.FileStore;
import java.util.Enumeration;

@WebFilter(filterName = "FilterAuth")
public class FilterAuth implements Filter {

    ServletContext context = null;

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        chain.doFilter(req, resp);
        String token = req.getParameter("token");
        if (token!=null) {
            HttpSession hs = (HttpSession) context.getAttribute(token);
            if (hs==null){
                loginErr((HttpServletResponse) resp,"无效的令牌！");
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
            }
        }else {
            loginErr((HttpServletResponse) resp,"从未登录！");
        }

    }

    public void init(FilterConfig config) throws ServletException {
        context = config.getServletContext();
    }

    public static void loginErr(HttpServletResponse response, String message){
        try {
            response.setStatus(405);
            Writer o = response.getWriter();
            o.write("Faild！"+  message);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
