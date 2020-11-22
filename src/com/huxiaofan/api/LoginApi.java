package com.huxiaofan.api;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Writer;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;


import static com.huxiaofan.api.md5Utils.newMD5;

@WebServlet(name = "LoginApi")
public class LoginApi extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //此接口用于新生成服务ID和查看服务列表
        //封装的http请求响应头
        httpUtils.httpUtil(request, response);

        Writer o = response.getWriter();

        //新的数据工具类对象
        dbUtils db = new dbUtils();
        //创建stmt类
        Statement stmt = db.getStatement();


        String type = request.getParameter("type");
        String u = request.getParameter("user");
        String p = request.getParameter("pass");

        if (type.equals("staff")) {
            //员工，管理员登录

            String sql = "SELECT eid,ename,epass,isadmin FROM staff WHERE eid=" + u;

            try {
                ResultSet rs = stmt.executeQuery(sql);
                rs.beforeFirst();

                String eid = null;
                String ename = null;
                String epass = null;
                String isadmin = null;

                while (rs.next()) {
                    eid = rs.getString(1);
                    ename = rs.getString(2);
                    epass = rs.getString(3);
                    isadmin = rs.getString(4);
                }

                if (eid != null && eid.equals(u)){
                    if (epass != null && epass.equals(p)){
                        //到这里为止是认证成功的情况

                        //获取session
                        HttpSession hs = request.getSession(true);


                        //md5加时间戳生成一个随机token
                        Date d = new Date();
                        double rd = Math.random();

                        String sec = String.valueOf(rd) + d.getTime();

                        //System.out.println(sec);
                        String token = newMD5(String.valueOf(sec));
                        System.out.println("随机token生成完毕： "+token);

                        hs.setAttribute(token,eid);
                        hs.setAttribute(token,ename);
                        hs.setAttribute(token,isadmin);

                        o.write("认证成功");


                    }else {
                        loginErr(response);
                    }
                }else {
                    loginErr(response);
                }

                rs.close();
                db.closeConnect();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }


        } else {


        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    public static void loginErr(HttpServletResponse response){
        try {
            Writer o = response.getWriter();
            o.write("认证失败！");
            response.setStatus(401);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
