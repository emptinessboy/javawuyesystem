package com.huxiaofan.api;

import com.alibaba.fastjson.JSONArray;
import sun.java2d.pipe.SpanShapeRenderer;

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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

@WebServlet(name = "UserApi")
public class UserApi extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //此方法用来添加和删除服务
        //封装的http请求响应头
        httpUtils.httpUtil(request, response);
        //定义输出对象
        Writer o = response.getWriter();

        String method = request.getParameter("method");
        String sid = request.getParameter("sid");
        String token = request.getParameter("token");
        HttpSession hs = (HttpSession) (getServletContext().getAttribute(token));
        String cno = (String) hs.getAttribute("cno");
        System.out.println(method + " " + cno + " " + sid);
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = sdf.format(d.getTime());
        if (method.equals("order")) {

            String s = "INSERT INTO orders" +
                    "(cno,sid,date)" +
                    "VALUES" +
                    "(\'" + cno + "\',\'" + sid + "\',\'" + date + "\')";

            System.out.println(s);
            //新的数据工具类对象
            dbUtils db = new dbUtils();
            //创建stmt类
            Statement stmt = db.getStatement();
            try {
                String find = "SELECT cno FROM orders where cno=" + cno;
                ResultSet rs = stmt.executeQuery(find);
                rs.beforeFirst();
                rs.last();
                int sum = rs.getRow();
                rs.close();
                if (sum > 0){
                    o.write("还有尚未处理的订单，订购失败！");
                    System.out.println("还有尚未处理的订单，订购失败！" + d);
                    response.setStatus(203);
                }
                else if (stmt.executeUpdate(s) == 0) {
                    o.write("Fail，订购失败！");
                    System.out.println("Fail，订购失败！" + d);
                    response.setStatus(202);
                } else {
                    response.setStatus(200);
                    System.out.println("OK，订购成功！" + sid);
                    System.out.println(d);
                    o.write("OK，订购成功！");
                }
            } catch (SQLException throwables) {
                o.write("Fail，订购失败！");
                System.out.println("Fail，订购失败！" + d);
                throwables.printStackTrace();
                response.setStatus(204);
            } finally {
                System.out.println("订购物业服务成功");
                //使用定义的工具类一键断开con和stmt连接
                db.closeConnect();
            }

        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //此接口用于新生成服务ID和查看服务列表
        //封装的http请求响应头
        httpUtils.httpUtil(request, response);

        Writer o = response.getWriter();


        String want = request.getParameter("want");
        try {
            if (want == null) {
                o.write("此接口需要参数，详情仔细管理员 晓帆 i@my.huxiaofan.com");
            } else if (want.equals("order")) {
                //新的数据工具类对象
                dbUtils db = new dbUtils();
                Statement stmt = db.getStatement();
                //result接口
                ResultSet rs;
                String token = request.getParameter("token");
                HttpSession hs = (HttpSession) getServletContext().getAttribute(token);
                String cno = (String) hs.getAttribute("cno");
                String s = "SELECT orders.sid,orders.date,service.sname FROM orders LEFT JOIN service ON orders.sid=service.sid WHERE cno=" + cno;
                System.out.println(s);
                rs = stmt.executeQuery(s);
                rs.beforeFirst();
                //使用alibaba的fastjson建立一个json对象
                JSONArray serviceJson = new JSONArray();
                while (rs.next()) {
                    //创建服务列表信息哈希表
                    HashMap<String, String> serviceList = new HashMap<String, String>();

                    String sid = rs.getString(1);
                    String date = rs.getString(2);
                    String sname = "未命名";
                    sname = rs.getNString(3);


                    serviceList.put("sid", sid);
                    serviceList.put("sname", sname);
                    serviceList.put("date", date);

                    //把hashmap对象添加到json数组中
                    serviceJson.add(serviceList);
                }
                //断开数据库连接
                rs.close();

                response.setStatus(200);
                //输出json
                o.write(serviceJson.toJSONString());

                System.out.println("输出进行中的服务列表成功");
                db.closeConnect();
            } else if (want.equals("slist")) {
                //新的数据工具类对象
                dbUtils db = new dbUtils();
                Statement stmt = db.getStatement();
                //result接口
                ResultSet rs;
                rs = stmt.executeQuery("SELECT * FROM service");
                rs.beforeFirst();

                //使用alibaba的fastjson建立一个json对象
                JSONArray serviceJson = new JSONArray();
                while (rs.next()) {
                    //创建服务列表信息哈希表
                    HashMap<String, String> serviceList = new HashMap<String, String>();

                    String sid = rs.getString(1);
                    String sname = rs.getString(2);
                    String sprice = rs.getString(3);
                    String sdesc = rs.getString(4);
                    String stime = rs.getString(5);

                    serviceList.put("sid", sid);
                    serviceList.put("sname", sname);
                    serviceList.put("sprice", sprice);
                    serviceList.put("sdesc", sdesc);
                    serviceList.put("stime", stime);

                    //把hashmap对象添加到json数组中
                    serviceJson.add(serviceList);
                }
                response.setStatus(200);
                //输出json
                o.write(serviceJson.toJSONString());
                //断开数据库连接
                rs.close();
                System.out.println("输出物业服务列表成功");
                db.closeConnect();
            }

        } catch (Exception throwables) {
            response.setStatus(502);
            throwables.printStackTrace();
        } finally {
            //使用定义的工具类一键断开con和stmt连接
            // db.closeConnect();
        }
    }
}
