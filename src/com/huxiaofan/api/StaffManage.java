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

@WebServlet(name = "StaffManage")
public class StaffManage extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//此方法用来添加和删除服务
        //封装的http请求响应头
        httpUtils.httpUtil(request, response);
        //定义输出对象
        Writer o = response.getWriter();

        //新的数据工具类对象
        dbUtils db = new dbUtils();
        //创建stmt类
        Statement stmt = db.getStatement();

        String method = request.getParameter("method");
        String eid = request.getParameter("id");

        if (method.equals("delete")) {

            // 此段代码判断参数中 method 若为 delete，则删除对应服务项目
            // String d = "delete from staff where eid = \"" + eid + "\"";

            //这里使用了leftjoin多表联合查询进行删除;
            String d = "delete staff,record from staff " +
                    "LEFT JOIN record ON staff.eid=record.staff " +
                    "where staff.eid = \"" + eid + "\"";

            System.out.println(d);
            try {
                if (stmt.executeUpdate(d) == 0) {
                    o.write("Fail，删除员工失败！");
                    System.out.println("Fail，删除员工失败！");
                    response.setStatus(202);
                } else {
                    System.out.println("OK，删除员工成功！" + eid);
                    System.out.println(d);
                    o.write("OK，删除员工成功！");
                }
            } catch (SQLException throwables) {
                o.write("Fail，删除员工失败！");
                System.out.println("Fail，删除员工失败！");
                throwables.printStackTrace();
                response.setStatus(204);
            } finally {
                System.out.println("删除员工成功");
                //使用定义的工具类一键断开con和stmt连接
                db.closeConnect();
            }
        } else {
            //反之则添加服务
            String ename = request.getParameter("name");
            String esex = request.getParameter("sex");
            String epass = request.getParameter("pass");
            String admin = request.getParameter("isadmin");
            int isadmin = 0;
            if (admin.equals("true")) {
                isadmin = 1;
            }
            System.out.println(method + " " + eid + " " + ename + " " + esex + " " + epass + " " + isadmin);

            String s = "INSERT INTO staff" +
                    "(eid,ename,esex,epass,isadmin)" +
                    "VALUES" +
                    "(\'" + eid + "\',\'" + ename + "\',\'" + esex + "\',\'" + epass + "\',\'" + isadmin + "\')";
            System.out.println(s);
            try {
                if (stmt.executeUpdate(s) == 0) {
                    o.write("Fail，插入失败！");
                    System.out.println("Fail，插入用户信息失败！");
                    response.setStatus(202);
                } else {
                    System.out.println("OK，新增成功！");
                    System.out.println(s);
                    o.write("OK，新增成功！");
                }
            } catch (SQLException throwables) {
                o.write("Fail，插入失败！");
                System.out.println("Fail，插入失败！");
                throwables.printStackTrace();
                response.setStatus(204);
            } finally {
                System.out.println("新增员工成功");
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


        //新的数据工具类对象
        dbUtils db = new dbUtils();
        //创建stmt类
        Statement stmt = db.getStatement();
        String want = request.getParameter("want");
        try {
            if (want == null) {
                o.write("此接口需要参数，详情仔细管理员 晓帆 i@my.huxiaofan.com");
            } else if (want.equals("eid")) {
                //result接口
                ResultSet rs;
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
            } else if (want.equals("elist")) {
                //result接口
                ResultSet rs;
                rs = stmt.executeQuery("SELECT * FROM staff");
                rs.beforeFirst();

                //使用alibaba的fastjson建立一个json对象
                JSONArray staffJson = new JSONArray();
                while (rs.next()) {
                    //创建服务列表信息哈希表
                    HashMap<String, String> staffList = new HashMap<String, String>();

                    String eid = rs.getString(1);
                    String ename = rs.getString(2);
                    String esex = rs.getString(3);
                    String esrore = rs.getString(4);

                    staffList.put("eid", eid);
                    staffList.put("ename", ename);
                    staffList.put("esex", esex);
                    staffList.put("esrore", esrore);
                    int isadmin = rs.getInt(6);
                    if (isadmin > 0) {
                        staffList.put("isadmin", "管理员");
                    } else {
                        staffList.put("isadmin", "普通员工");
                    }

                    //把hashmap对象添加到json数组中
                    staffJson.add(staffList);
                }

                //输出json
                o.write(staffJson.toJSONString());
                //断开数据库连接
                rs.close();
                System.out.println("输出员工列表成功");
            } else if (want.equals("escore")) {
                //result接口
                ResultSet rs;
                rs = stmt.executeQuery("SELECT eid,ename,esex,escore FROM staff ORDER BY escore DESC");
                rs.beforeFirst();

                //使用alibaba的fastjson建立一个json对象
                JSONArray staffJson = new JSONArray();
                while (rs.next()) {
                    //创建服务列表信息哈希表
                    HashMap<String, String> staffList = new HashMap<String, String>();

                    String eid = rs.getString(1);
                    String ename = rs.getString(2);
                    String esex = rs.getString(3);
                    String esrore = rs.getString(4);

                    staffList.put("eid", eid);
                    staffList.put("ename", ename);
                    staffList.put("esex", esex);
                    staffList.put("esrore", esrore);

                    //把hashmap对象添加到json数组中
                    staffJson.add(staffList);
                }
                //输出json
                o.write(staffJson.toJSONString());
                //断开数据库连接
                rs.close();
                System.out.println("输出员工绩效成功");
            }

        } catch (Exception throwables) {
            throwables.printStackTrace();
        } finally {
            //使用定义的工具类一键断开con和stmt连接
            db.closeConnect();
        }
    }
}
