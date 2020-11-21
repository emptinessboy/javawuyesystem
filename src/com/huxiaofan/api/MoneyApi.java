package com.huxiaofan.api;

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

@WebServlet(name = "MoneyApi")
public class MoneyApi extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //封装的http请求响应头
        httpUtils.httpUtil(request,response);
        //定义输出对象
        Writer o = response.getWriter();
        //result接口
        ResultSet rs;
        //新的数据工具类对象
        dbUtils db = new dbUtils();
        //创建stmt类
        Statement stmt = db.getStatement();

        String method = request.getParameter("method");
        String cno = request.getParameter("uid");
        String sid = request.getParameter("service");
        String date = request.getParameter("date").substring(0, 19);
        String times = request.getParameter("times");
        String staff = request.getParameter("staff");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date d = null;
        try {
            d = sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //这句话加8小时
        d.setTime(d.getTime() + 8 * 60 * 60 * 1000);
        //System.out.println(d);
        //时间对象转字符串
        date = sdf.format(d);
        System.out.println(method + " " + cno + " " + sid + " " + date + " " + times + " " + staff);

//        if (method.equals("delete")) {
//            //此段代码判断参数中 method 若为 delete，则删除对应服务项目
//            String d = "delete from service where sid = \"" + sid + "\"";
//            System.out.println(d);
//            try {
//                if (stmt.executeUpdate(d) == 0) {
//                    o.write("Fail，删除失败！");
//                    System.out.println("Fail，删除失败！" + d);
//                    response.setStatus(202);
//                } else {
//                    System.out.println("OK，删除成功！" + sid);
//                    System.out.println(d);
//                    o.write("OK，删除成功！");
//                }
//            } catch (SQLException throwables) {
//                o.write("Fail，删除失败！");
//                System.out.println("Fail，删除失败！" + d);
//                throwables.printStackTrace();
//                response.setStatus(204);
//            }finally {
//                System.out.println("删除物业服务成功");
//                //使用定义的工具类一键断开con和stmt连接
//                db.closeConnect();
//            }
//        } else {
//            //反之则添加服务
//            String s = "INSERT INTO service" +
//                    "(sid,sname,sprice,sdesc,stime)" +
//                    "VALUES" +
//                    "(\'" + sid + "\',\'" + sname + "\',\'" + sprice + "\',\'" + sdesc + "\',\'" + stime + "\')";
//            try {
//                if (stmt.executeUpdate(s) == 0) {
//                    o.write("Fail，插入失败！");
//                    System.out.println("Fail，插入用户信息失败！" + s);
//                    response.setStatus(202);
//                } else {
//                    System.out.println("OK，新增成功！" + sid);
//                    System.out.println(s);
//                    o.write("OK，新增成功！");
//                }
//            } catch (SQLException throwables) {
//                o.write("Fail，插入失败！");
//                System.out.println("Fail，插入失败！" + s);
//                throwables.printStackTrace();
//                response.setStatus(204);
//            }finally {
//                System.out.println("新增物业服务成功");
//                //使用定义的工具类一键断开con和stmt连接
//                db.closeConnect();
//            }
//        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
