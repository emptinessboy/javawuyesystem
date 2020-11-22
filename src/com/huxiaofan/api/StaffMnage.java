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
import java.sql.Statement;
import java.util.HashMap;

@WebServlet(name = "StaffMnage")
public class StaffMnage extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //此接口用于新生成服务ID和查看服务列表
        //封装的http请求响应头
        httpUtils.httpUtil(request, response);

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
            } else if (want.equals("eid")) {
                rs = stmt.executeQuery("SELECT eid FROM staff");
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
                System.out.println("输出新员工ID成功");
            } else if (want.equals("slist")) {
//                rs = stmt.executeQuery("SELECT * FROM service");
//                rs.beforeFirst();
//
//                //使用alibaba的fastjson建立一个json对象
//                JSONArray serviceJson = new JSONArray();
//                while (rs.next()) {
//                    //创建服务列表信息哈希表
//                    HashMap<String, String> serviceList = new HashMap<String, String>();
//
//                    String sid = rs.getString(1);
//                    String sname = rs.getString(2);
//                    String sprice = rs.getString(3);
//                    String sdesc = rs.getString(4);
//                    String stime = rs.getString(5);
//
//                    serviceList.put("sid", sid);
//                    serviceList.put("sname", sname);
//                    serviceList.put("sprice", sprice);
//                    serviceList.put("sdesc", sdesc);
//                    serviceList.put("stime", stime);
//
//                    //把hashmap对象添加到json数组中
//                    serviceJson.add(serviceList);
//                }
//                //输出json
//                o.write(serviceJson.toJSONString());
//                //断开数据库连接
//                rs.close();
//                System.out.println("输出物业服务列表成功");
            }

        } catch (Exception throwables) {
            throwables.printStackTrace();
        } finally {
            //使用定义的工具类一键断开con和stmt连接
            db.closeConnect();
        }
    }
}