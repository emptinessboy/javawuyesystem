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
        String sid = request.getParameter("sid");
        String sname = request.getParameter("name");
        String sprice = request.getParameter("price");
        String sdesc = request.getParameter("desc");
        String stime = String.valueOf(0);
        System.out.println(method + " " + sid + " " + sname + " " + sprice + " " + sdesc + " " + stime);

        if (method.equals("delete")) {
            //此段代码怕毛短参数中 method 若为 delete，则删除对应服务项目
            String d = "delete from service where sid = \"" + sid + "\"";
            System.out.println(d);
            try {
                if (stmt.executeUpdate(d) == 0) {
                    o.write("Fail，删除失败！");
                    System.out.println("Fail，删除失败！" + d);
                    response.setStatus(202);
                } else {
                    System.out.println("OK，删除成功！" + sid);
                    System.out.println(d);
                    o.write("OK，删除成功！");
                }
            } catch (SQLException throwables) {
                o.write("Fail，删除失败！");
                System.out.println("Fail，删除失败！" + d);
                throwables.printStackTrace();
                response.setStatus(204);
            }finally {
                System.out.println("删除物业服务成功");
                //使用定义的工具类一键断开con和stmt连接
                db.closeConnect();
            }
        } else {
            //反之则添加服务
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
                    System.out.println(s);
                    o.write("OK，新增成功！");
                }
            } catch (SQLException throwables) {
                o.write("Fail，插入失败！");
                System.out.println("Fail，插入失败！" + s);
                throwables.printStackTrace();
                response.setStatus(204);
            }finally {
                System.out.println("新增物业服务成功");
                //使用定义的工具类一键断开con和stmt连接
                db.closeConnect();
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
        String want = request.getParameter("want");
        try {
            if (want == null) {
                o.write("此接口需要参数，详情仔细管理员 晓帆 i@my.huxiaofan.com");
            } else if (want.equals("sid")) {
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
                System.out.println("输出新物业服务ID成功");
            } else if (want.equals("slist")) {
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
                //输出json
                o.write(serviceJson.toJSONString());
                //断开数据库连接
                rs.close();
                System.out.println("输出物业服务列表成功");
            }

        } catch (Exception throwables) {
            throwables.printStackTrace();
        } finally {
            //使用定义的工具类一键断开con和stmt连接
            db.closeConnect();
        }
    }
}
