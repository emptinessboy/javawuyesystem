package com.huxiaofan.api;
//此类实现用户列表查询，用户信息更新和用户删除

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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import com.alibaba.fastjson.*;

@WebServlet(name = "ListMembers")
public class ListMembers extends HttpServlet {

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

        //如果参数中method为删除，则操作mysql删除数据
        if (request.getParameter("method").equals("delete")) {
            String cno = request.getParameter("cno");
            String d = "delete from members where cno = \"" + cno + "\"";
            try {
                if (stmt.executeUpdate(d) == 0) {
                    o.write("Fail，删除失败！");
                    System.out.println("Fail，删除失败！" + d);
                    response.setStatus(202);
                }else {
                    System.out.println("OK，删除成功！" + cno);
                    System.out.println(d);
                    o.write("OK，删除成功！");
                }
            } catch (SQLException throwables) {
                o.write("Fail，删除失败！");
                System.out.println("Fail，删除失败！" + d);
                throwables.printStackTrace();
                response.setStatus(204);
            }
        } else if (request.getParameter("method").equals("modify")) {
            //从参数获取前端需要修改的学生ID
            String cno = request.getParameter("row[cno]");
            String cname = request.getParameter("row[cname]");
            String caddress = request.getParameter("row[caddress]");
            //因为前端来的数据形如 cregtime="1986-01-07T16:00:00.000Z"需要对其进行处理
            String cregtime = request.getParameter("row[cregtime]").substring(0, 19);
            String csex = request.getParameter("row[csex]");

            System.out.println("取得的参数为：" + cno + " " + cname + " " + caddress + " " + cregtime + " " + csex);
            //用于sql出错分析
            // String m = "update members set cname=\"" + cname + "\", csex=\"" + csex + "\", caddress=\"" + caddress + "\", cregtime=\"" + cregtime + "\" where cno=\"" + cno + "\"";

            try {
                //先转换为时间对象是为了解决时区UTF-8同步的的问题
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                Date d = sdf.parse(cregtime);
                //这句话加8小时
                d.setTime(d.getTime() + 8 * 60 * 60 * 1000);
                //System.out.println(d);
                //时间对象转字符串
                cregtime = sdf.format(d);
                //sql语句拼接
                String m = "update members set cname=\"" + cname + "\", csex=\"" + csex + "\", caddress=\"" + caddress + "\", cregtime=\"" + cregtime + "\" where cno=\"" + cno + "\"";
                System.out.println(m);
                //打印行数
                int count = stmt.executeUpdate(m);
                if (count > 0) {
                    o.write("修改成功，影响行数：" + count);
                }
                System.out.println("修改成功，影响行数：" + count);
            } catch (SQLException | ParseException throwables) {
                o.write("Fail，修改失败！");
                System.out.println("Fail，修改失败！");
                response.setStatus(204);
                throwables.printStackTrace();
            }
        }

        //使用定义的工具类一键断开con和stmt连接
        db.closeConnect();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

        dbUtils db = new dbUtils();
        try {
            Statement stmt = db.getStatement();
            rs = stmt.executeQuery("SELECT * FROM members");

            rs.beforeFirst();
            //使用alibaba的fastjson建立一个json对象
            JSONArray membersJson = new JSONArray();

            while (rs.next()) {
                //创建用户信息哈希表
                HashMap<String, String> members = new HashMap<String, String>();
                String cno = rs.getString(1);
                String cname = rs.getString(2);
                String csex = rs.getString(3);
                String caddress = rs.getString(4);
                String cregtime = rs.getString(5);
                String cmoney = rs.getString(6);
                members.put("cno", cno);
                members.put("cname", cname);
                members.put("csex", csex);
                members.put("caddress", caddress);
                members.put("cregtime", cregtime);
                members.put("cmoney", cmoney);
                //把hashmap对象添加到json数组中
                membersJson.add(members);
            }
            //字符串输出json内容
            o.write(membersJson.toJSONString());
            //断开数据库连接
            rs.close();
            //使用定义的工具类一键断开con和stmt连接
            db.closeConnect();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
