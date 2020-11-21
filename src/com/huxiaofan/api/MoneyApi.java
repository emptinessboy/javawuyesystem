package com.huxiaofan.api;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.sql.Connection;
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

        //需要用到事务查询，就不使用之前的工具类了
        Connection con = null;
        Statement stmt = null;
        try {
            con = MySql.getConnection();
            System.out.println("Statement建立成功");
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        //因为涉及“钱”的操作
        //事务默认就是自动提交的。 需要开启事务，关闭自动提交。
        try {
            con.setAutoCommit(false);
            System.out.println("MoneyAPI关闭自动提交成功");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        //创建stmt类
//        Statement stmt = db.getStatement();

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
            //
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

                //计算服务总金额 = 金额*次数
                smoney = Double.parseDouble(times) * smoney;
                System.out.println("smoney："+smoney);

                //p = pay
                Double cmoney = umoney - smoney;
                String p = "update members set cmoney=" + cmoney + " where cno = " + cno;

                if (rid == null) {
                    o.write("Fail，用户ID不存在！");
                    System.out.println("Fail，用户ID不存在！" + f);
                    response.setStatus(205);
                } else if (umoney < smoney) {
                    o.write("Fail，用户余额不足！");
                    System.out.println("Fail，用户余额不足！");
                    response.setStatus(206);
                } else if (stmt.executeUpdate(p) == 0) {
                    o.write("Fail，用户扣款失败！");
                    System.out.println("Fail，用户扣款失败！" + p);
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
                    //没问题的话才提交数据库查询
                    con.commit();
                    System.out.println("OK，新增物业费记录成功！" + sid);
                    System.out.println(a);
                    o.write("OK，新增物业费记录成功！");
                }
            } catch (SQLException throwables) {
                try {
                    con.rollback();
                    System.out.println("遇到异常，回滚数据库成功");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                o.write("Fail，插入失败！");
                System.out.println("Fail，插入物业费记录失败！");
                throwables.printStackTrace();
                response.setStatus(204);
            } finally {
                //使用定义的工具类一键断开con和stmt连接
                try {
                    stmt.close();
                    con.close();
                    System.out.println("断开事务的mysql连接成功");
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
