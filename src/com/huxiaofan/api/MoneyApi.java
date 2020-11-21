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
        httpUtils.httpUtil(request, response);
        //定义输出对象
        Writer o = response.getWriter();

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

        if (method.equals("delete")) {
//            //此段代码判断参数中 method 若为 delete，则删除对应服务项目
////            String d = "delete from service where sid = \"" + sid + "\"";
////            System.out.println(d);
////            try {
////                if (stmt.executeUpdate(d) == 0) {
////                    o.write("Fail，删除失败！");
////                    System.out.println("Fail，删除失败！" + d);
////                    response.setStatus(202);
////                } else {
////                    System.out.println("OK，删除成功！" + sid);
////                    System.out.println(d);
////                    o.write("OK，删除成功！");
////                }
////            } catch (SQLException throwables) {
////                o.write("Fail，删除失败！");
////                System.out.println("Fail，删除失败！" + d);
////                throwables.printStackTrace();
////                response.setStatus(204);
////            }finally {
////                System.out.println("删除物业服务成功");
////                //使用定义的工具类一键断开con和stmt连接
////                db.closeConnect();
////            }
        } else if (method.equals("pay")) {

            //增加物业服务次数记录
            //使用sql语句内置的运算可以避免把整个表遍历再update的性能损耗
            //f = find
            String f = "select cno from members where cno = " + cno + " limit 1";
            //howmuch h1查询用户余额
            //查询服务价格h2
            String h1 = "select cmoney from members where cno = " + cno + " limit 1";
            String h2 = "select sprice from service where sid = " + sid + " limit 1";

            //m = modify
            String m = "update service set stime=stime+1 where sid = " + sid;

            //添加物业费记录
            //a = add
            String a = "INSERT INTO record" +
                    "(method,cno,sid,date,times,staff)" +
                    "VALUES" +
                    "(\'" + method + "\',\'" + cno + "\',\'" + sid + "\',\'" + date + "\',\'" + times + "\',\'" + staff + "\')";
            //result接口
            ResultSet rs;
            try {
                String rid = null;
                Double umoney = Double.valueOf(0);
                Double smoney = Double.valueOf(0);
                rs = stmt.executeQuery(f);
                while (rs.next()) {
                    rid = rs.getString(1);
                }
                rs.close();
                rs = stmt.executeQuery(h1);
                while (rs.next()) {
                    umoney = Double.parseDouble(rs.getString(1));
                }
                rs.close();
                rs = stmt.executeQuery(h2);
                while (rs.next()) {
                    smoney = Double.parseDouble(rs.getString(1));
                }
                rs.close();
                System.out.println("用户余额：" + umoney + " 服务金额：" + smoney);

                //p = pay
                Double cmoney = umoney - smoney;
                String p = "update members set cmoney=cmoney-" + cmoney + " where cno = " + cno;

                if (rid == null) {
                    o.write("Fail，用户ID不存在！");
                    System.out.println("Fail，用户ID不存在！" + f);
                    response.setStatus(205);
                } else if (umoney < smoney) {
                    o.write("Fail，用户余额不足！");
                    System.out.println("Fail，用户余额不足！" + f);
                    response.setStatus(206);
                } else if (stmt.executeUpdate(p) == 0) {
                    o.write("Fail，用户扣款失败！");
                    System.out.println("Fail，用户扣款失败！" + a);
                    response.setStatus(207);
                } else if (stmt.executeUpdate(a) == 0) {
                    o.write("Fail，修改服务次数失败！");
                    System.out.println("Fail，修改服务次数失败！" + a);
                    response.setStatus(202);
                } else if (stmt.executeUpdate(m) == 0) {
                    o.write("Fail，插入失败！");
                    System.out.println("Fail，插入用户名密码失败！" + m);
                    response.setStatus(202);
                } else {
                    System.out.println("OK，新增物业费记录成功！" + sid);
                    System.out.println(a);
                    o.write("OK，新增物业费记录成功！");
                }
            } catch (SQLException throwables) {
                o.write("Fail，插入失败！");
                System.out.println("Fail，插入物业费记录失败！");
                throwables.printStackTrace();
                response.setStatus(204);
            } finally {
                System.out.println("新增物业费记录成功");
                //使用定义的工具类一键断开con和stmt连接
                db.closeConnect();
            }
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
