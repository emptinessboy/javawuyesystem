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
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

@WebServlet(name = "UserApi")
public class UserApi extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //此方法用来订购物业服务
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
                if (sum > 0) {
                    o.write("还有尚未处理的订单，订购失败！");
                    System.out.println("还有尚未处理的订单，订购失败！" + d);
                    response.setStatus(203);
                } else if (stmt.executeUpdate(s) == 0) {
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
        } else if (method.equals("confirm")) {
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
            String staff = "";
            String getstaff = "select staff from orders where cno=" + cno;

            //时间对象转字符串
            date = sdf.format(d);

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

            //result接口
            ResultSet rs;
            try {
                System.out.println("开始查询");
                rs = stmt.executeQuery(getstaff);
                while (rs.next()) {
                    staff = rs.getString(1);
                }
                rs.close();
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
                        "(\'" + "pay" + "\',\'" + cno + "\',\'" + sid + "\',\'" + date + "\',\'" + times + "\',\'" + staff + "\',\'" + smoney + "\')";

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
        //此接口用于新生成服务ID和查看服务列表
        //封装的http请求响应头
        httpUtils.httpUtil(request, response);
        Writer o = response.getWriter();

        String want = request.getParameter("want");
        try {
            if (want == null) {
                //此接口输出物业费余额
                //新的数据工具类对象
                dbUtils db = new dbUtils();
                Statement stmt = db.getStatement();
                //result接口
                ResultSet rs;
                String token = request.getParameter("token");
                HttpSession hs = (HttpSession) getServletContext().getAttribute(token);
                String cno = (String) hs.getAttribute("cno");
                String s = "SELECT cmoney FROM members WHERE cno=" + cno;
                System.out.println(s);
                rs = stmt.executeQuery(s);
                rs.beforeFirst();
                //使用alibaba的fastjson建立一个json对象
                JSONArray serviceJson = new JSONArray();
                while (rs.next()) {
                    //创建服务列表信息哈希表
                    HashMap<String, String> serviceList = new HashMap<String, String>();
                    String cmoney = rs.getString(1);
                    serviceList.put("cmoney", cmoney);
                    //把hashmap对象添加到json数组中
                    serviceJson.add(serviceList);
                }
                //断开数据库连接
                rs.close();
                response.setStatus(200);
                //输出json
                o.write(serviceJson.toJSONString());
                System.out.println("输出用户余额列表成功");
                db.closeConnect();
            } else if (want.equals("order")) {
                //新的数据工具类对象
                dbUtils db = new dbUtils();
                Statement stmt = db.getStatement();
                //result接口
                ResultSet rs;
                String token = request.getParameter("token");
                HttpSession hs = (HttpSession) getServletContext().getAttribute(token);
                String cno = (String) hs.getAttribute("cno");
                String s = "SELECT orders.sid,orders.date,service.sname,orders.status FROM orders LEFT JOIN service ON orders.sid=service.sid WHERE cno=" + cno;
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
                    String status = rs.getString(4);

                    serviceList.put("sid", sid);
                    serviceList.put("sname", sname);
                    serviceList.put("date", date);
                    serviceList.put("status", status);

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
