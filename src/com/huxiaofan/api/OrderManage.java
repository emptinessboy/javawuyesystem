package com.huxiaofan.api;

import com.alibaba.fastjson.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Writer;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

@WebServlet(name = "OrderManage")
public class OrderManage extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //此方法用来接受和结束工单
        //封装的http请求响应头
        httpUtils.httpUtil(request, response);
        //定义输出对象
        Writer o = response.getWriter();

        String method = request.getParameter("method");
        String token = request.getParameter("token");
        HttpSession hs = (HttpSession) getServletContext().getAttribute(token);
        String staff = (String) hs.getAttribute("eid");
        String id = request.getParameter("id");

        if (method.equals("accept")) {

            String ac = "update orders set status=\"" + "2" + "\", staff=\"" + staff + "\" where id=\"" + id + "\"";

            System.out.println(ac);
            //新的数据工具类对象
            dbUtils db = new dbUtils();
            //创建stmt类
            Statement stmt = db.getStatement();
            try {
                if (stmt.executeUpdate(ac) == 0) {
                    o.write("Fail，接单失败！");
                    System.out.println("Fail，接单失败！");
                    response.setStatus(202);
                } else {
                    response.setStatus(200);
                    System.out.println("OK，接单成功！");
                    System.out.println(ac);
                    o.write("OK，接单成功！");
                }
            } catch (SQLException throwables) {
                o.write("Fail，接单失败！");
                System.out.println("Fail，接单失败！");
                throwables.printStackTrace();
                response.setStatus(204);
            } finally {
                System.out.println("接单物业服务成功");
                //使用定义的工具类一键断开con和stmt连接
                db.closeConnect();
            }
        } else if (method.equals("close")) {
            //此api提供了后台管理员操作的结单功能
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
            //用来支付物业费
            String times = "1";
            String cno = null;
            String sid = null;
            String getinfo = "select cno,sid from orders where id=" + id;

            //获取当前时间字符串
            Date d = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String date = sdf.format(d.getTime());
            //时间对象转字符串
            date = sdf.format(d);

            //result接口
            ResultSet rs;
            try {
                System.out.println("开始查询");
                // 从 orders 表获取 cno sid
                rs = stmt.executeQuery(getinfo);
                while (rs.next()) {
                    cno = rs.getString(1);
                    sid = rs.getString(2);
                }
                rs.close();

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
                System.out.println(method + " " + cno + " " + sid + " " + date + " " + times + " " + staff);
                System.out.println("用户余额：" + umoney + " 服务金额：" + smoney);

                //计算服务总金额 = 金额*次数
                smoney = Double.parseDouble(times) * smoney;
                System.out.println("smoney：" + smoney);

                //p = pay
                Double cmoney = umoney - smoney;
                String p = "update members set cmoney=" + cmoney + " where cno = " + cno;

                //添加物业费记录
                //a = add
                String a = "INSERT INTO record" +
                        "(method,cno,sid,date,times,staff,money)" +
                        "VALUES" +
                        "(\'" + method + "\',\'" + cno + "\',\'" + sid + "\',\'" + date + "\',\'" + times + "\',\'" + staff + "\',\'" + smoney + "\')";

                //删除对应工单
                String delorder = "DELETE orders FROM orders WHERE cno=" + cno;

                if (rid == null) {
                    response.setStatus(205);
                    o.write("Fail，用户ID不存在！");
                    System.out.println("Fail，用户ID不存在！" + f);
                } else if (umoney < smoney) {
                    response.setStatus(206);
                    o.write("Fail，用户余额不足！");
                    System.out.println("Fail，用户余额不足！");
                } else if (stmt.executeUpdate(p) == 0) {
                    response.setStatus(207);
                    o.write("Fail，用户扣款失败！");
                    System.out.println("Fail，用户扣款失败！" + p);
                } else if (stmt.executeUpdate(a) == 0) {
                    response.setStatus(202);
                    o.write("Fail，插入服务记录失败！");
                    System.out.println("Fail，插入服务记录失败！" + a);
                } else if (stmt.executeUpdate(m) == 0) {
                    response.setStatus(202);
                    o.write("Fail，修改服务次数失败！");
                    System.out.println("Fail，修改服务次数失败！" + m);
                } else if (stmt.executeUpdate(delorder) == 0) {
                    response.setStatus(208);
                    o.write("Fail，清除工单失败！");
                    System.out.println("Fail，清除工单失败！" + p);
                } else {
                    //增加员工绩效
                    //绩效增加失败不影响整体执行
                    Double jx = Double.valueOf(times) * 5;
                    String s = "update staff set escore=escore+" + jx + " where eid = " + staff;
                    try {
                        stmt.executeUpdate(s);
                        System.out.println("员工绩效增加成功！" + s);
                    } catch (Exception e) {
                        System.out.println(e);
                    }

                    //没问题的话才提交数据库查询
                    con.commit();
                    response.setStatus(200);
                    o.write("OK，用户确认结单成功，新增物业费记录成功！");
                    System.out.println("OK，用户确认结单成功，新增物业费记录成功！" + sid);
                    System.out.println(a);
                }

            } catch (SQLException throwables) {
                try {
                    con.rollback();
                    System.out.println("遇到异常，回滚数据库成功");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                response.setStatus(204);
                o.write("Fail，结单失败！");
                System.out.println("Fail，结单失败！");
                throwables.printStackTrace();
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
