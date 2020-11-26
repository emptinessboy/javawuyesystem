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

@WebServlet(name = "OrderManage")
public class OrderManage extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //此接口返回工单列表
        //封装的http请求响应头
        httpUtils.httpUtil(request, response);

        //定义输出对象
        Writer o = response.getWriter();
        dbUtils db = new dbUtils();
        Statement stmt = db.getStatement();
        //result接口
        ResultSet rs;
        try {
            rs = stmt.executeQuery("SELECT * FROM orders");
            rs.beforeFirst();
            //使用alibaba的fastjson建立一个json对象
            JSONArray orderJson = new JSONArray();

            while (rs.next()) {
                //创建用户信息哈希表
                HashMap<String, String> orders = new HashMap<String, String>();
                String id = rs.getString(1);
                String cno = rs.getString(2);
                String sid = rs.getString(3);
                String date = rs.getString(4);
                String status = rs.getString(5);
                String staff = rs.getString(6);
                orders.put("id", id);
                orders.put("cno", cno);
                orders.put("sid", sid);
                orders.put("date", date);
                orders.put("status", status);
                orders.put("staff", staff);
                //把hashmap对象添加到json数组中
                orderJson.add(orders);
            }
            //字符串输出json内容
            response.setStatus(200);
            o.write(orderJson.toJSONString());
            //断开数据库连接
            rs.close();
            //使用定义的工具类一键断开con和stmt连接
            //db.closeConnect();
            System.out.println("获取工单列表成功");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            //使用定义的工具类一键断开con和stmt连接
            db.closeConnect();
        }
    }
}
