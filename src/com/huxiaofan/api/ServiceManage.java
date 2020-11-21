package com.huxiaofan.api;

import com.alibaba.fastjson.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

@WebServlet(name = "ServiceManage")
public class ServiceManage extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json; charset=utf-8");
        //允许跨域请求
        response.setHeader("Access-Control-Allow-Origin", "*"); //  这里最好明确的写允许的域名
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:8081"); //  这里最好明确的写允许的域名
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type,Access-Token,Authorization,ybg");
        //定义输出对象
        Writer o = response.getWriter();
        //result接口
        ResultSet rs;
        //新的数据工具类对象
        dbUtils db = new dbUtils();
        //创建stmt类
        Statement stmt = db.getStatement();

        String method = request.getParameter("method");
        String sid = request.getParameter("id");
        String sname = request.getParameter("name");
        String sprice = request.getParameter("price");
        String sdesc = request.getParameter("desc");
        String stime = String.valueOf(0);
        System.out.println(sid + " " + sname + " " + sprice + " " + sdesc + " " + stime);

        if (method.equals("delete")) {
        } else {
            String s = "INSERT INTO service" +
                    "(sid,sname,sprice,sdesc,stime)" +
                    "VALUES" +
                    "(\'" + sid + "\',\'" + sname + "\',\'" + sprice + "\',\'" + sdesc + "\',\'" + stime + "\')";
            try {
                if (stmt.executeUpdate(s) == 0) {
                    o.write("Fail，插入失败！");
                    System.out.println("Fail，插入用户信息失败！" + s);
                    response.setStatus(202);
                } else {
                    System.out.println("OK，新增成功！" + sid);
                    System.out.println(s + " " + s);
                    o.write("OK，新增成功！");
                }
            } catch (SQLException throwables) {
                o.write("Fail，插入失败！");
                System.out.println("Fail，插入失败！" + s);
                throwables.printStackTrace();
                response.setStatus(204);
            }
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //这个方法就可以返回一个新的用户ID(大于目前任何已有的用户ID)
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json; charset=utf-8");
        //允许跨域请求
        response.setHeader("Access-Control-Allow-Origin", "*"); //  这里最好明确的写允许的域名
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:8081"); //  这里最好明确的写允许的域名
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type,Access-Token,Authorization,ybg");

        Writer o = response.getWriter();


        //result接口
        ResultSet rs;
        //新的数据工具类对象
        dbUtils db = new dbUtils();
        //创建stmt类
        Statement stmt = db.getStatement();

        try {
            rs = stmt.executeQuery("SELECT sid FROM service");
            rs.beforeFirst();
            Long maxid = 0L;
            while (rs.next()) {
                String sid = rs.getString(1);
                if (maxid < Long.parseLong(sid)) {
                    maxid = Long.parseLong(sid);
                }
            }
            maxid++;

            HashMap<String, String> newid = new HashMap<String, String>();
            newid.put("newid", maxid.toString());
            //使用alibaba的fastjson建立一个json对象
            JSONArray newidJson = new JSONArray();
            newidJson.add(newid);
            //输出json
            o.write(newidJson.toJSONString());
            //断开数据库连接
            rs.close();
            //使用定义的工具类一键断开con和stmt连接
            db.closeConnect();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
