package com.huxiaofan.api;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Writer;
import java.util.Enumeration;

@WebFilter(filterName = "FilterUser")
public class FilterUser implements Filter {

    ServletContext context = null;

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String token = req.getParameter("token");
        if (token != null) {
            HttpSession hs = (HttpSession) context.getAttribute(token);
            if (hs == null) {
                loginErr((HttpServletResponse) resp, "无效的令牌！");
                return;
            } else {
                try {
                    if (hs.getAttribute("cno") == null) {
                        loginErr((HttpServletResponse) resp, "需要普通用户登录访问！");
                        return;
                    }
                    //获取session中所有的键值对
                    Enumeration enumerationB = hs.getAttributeNames();
                    //方法二：通过for循环进行遍历
                    while (enumerationB.hasMoreElements()) {
                        // 获取session的属性名称
                        String name = enumerationB.nextElement().toString();
                        // 根据键值取session中的值
                        Object value = hs.getAttribute(name);
                        // 打印结果
                        System.out.println("----------" + name + "------------" + value);
                    }
                    //放行
                    chain.doFilter(req, resp);

                } catch (Exception e) {
                    loginErr((HttpServletResponse) resp, "令牌已过期！");
                }
            }
        } else {
            loginErr((HttpServletResponse) resp, "从未登录！");
            return;
        }

    }

    public void init(FilterConfig config) throws ServletException {
        context = config.getServletContext();
    }

    public static void loginErr(HttpServletResponse response, String message) {
        response.setContentType("application/json; charset=utf-8");
        //允许跨域请求
        //response.setHeader("Access-Control-Allow-Origin", "*"); //  这里最好明确的写允许的域名
        response.setHeader("Access-Control-Allow-Origin", "https://wyglxt.app.huxiaofan.com"); //  这里最好明确的写允许的域名
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:8081"); //  这里最好明确的写允许的域名
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT, HEAD");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Author", "huxiaofan");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type,Access-Token,Authorization,ybg,token");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        try {
            response.setStatus(405);
            Writer o = response.getWriter();
            o.write("Faild！" + message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
