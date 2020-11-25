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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

@WebServlet(name = "ShowData")
public class ShowData extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //此接口返回统计信息
        //封装的http请求响应头
        httpUtils.httpUtil(request, response);

        //定义输出对象
        Writer o = response.getWriter();
        dbUtils db = new dbUtils();
        Statement stmt = db.getStatement();
        //result接口
        ResultSet rs;
        //使用alibaba的fastjson建立一个json对象
        JSONArray showJson = new JSONArray();
        try {
            rs = stmt.executeQuery("SELECT count(cno),sum(cmoney) FROM members");
            rs.beforeFirst();
            while (rs.next()) {
                //创建用户信息哈希表
                HashMap<String, String> count = new HashMap<String, String>();
                String count_cno = rs.getString(1);
                String count_cmoney = rs.getString(2);
                //添加到哈希表
                count.put("count_cno", count_cno);
                count.put("count_cmoney", count_cmoney);
                //把hashmap对象添加到json数组中
                showJson.add(count);
                System.out.println("用户总数和用户总钱数统计完毕");
            }
            rs.close();

            rs = stmt.executeQuery("SELECT sum(times),sum(money) FROM record");
            rs.beforeFirst();
            while (rs.next()) {
                //创建用户信息哈希表
                HashMap<String, String> count = new HashMap<String, String>();
                String count_allstimes = rs.getString(1);
                String count_allsmoney = rs.getString(2);
                //添加到哈希表
                count.put("count_allstimes", count_allstimes);
                count.put("count_allsmoney", count_allsmoney);
                //把hashmap对象添加到json数组中
                showJson.add(count);
                System.out.println("总服务次数和总流水统计完毕");
            }
            rs.close();

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            //新时间对象
            Date d = new Date();
            System.out.println("当前时间为：" + sdf.format(d.getTime()));
            //统计图表第七天的时间戳
            Long d7 = d.getTime();
            //根据第七天时间戳计算每天的时间戳
            Long d6 = d7 - 24 * 60 * 60 * 1000;
            Long d5 = d6 - 24 * 60 * 60 * 1000;
            Long d4 = d5 - 24 * 60 * 60 * 1000;
            Long d3 = d4 - 24 * 60 * 60 * 1000;
            Long d2 = d3 - 24 * 60 * 60 * 1000;
            Long d1 = d2 - 24 * 60 * 60 * 1000;
            System.out.println("7天前的时间为：" + sdf.format(d1));
            //格式化时间字符串
            String day7 = sdf.format(d7).substring(0, 10);
            String day6 = sdf.format(d6).substring(0, 10);
            String day5 = sdf.format(d5).substring(0, 10);
            String day4 = sdf.format(d4).substring(0, 10);
            String day3 = sdf.format(d3).substring(0, 10);
            String day2 = sdf.format(d2).substring(0, 10);
            String day1 = sdf.format(d1).substring(0, 10);
            //组合日期数组
            String days[] = {day1, day2, day3, day4, day5, day6, day7};
            //建立输出的哈希表 这里哈希表的格式和之前不太一样了。使用字符串数组了
            HashMap<String, String[]> out = new HashMap<>();
            out.put("days", days);
            //数据库查询时间 方法 钱
            String cx = "SELECT date,method,money FROM record WHERE record.date BETWEEN '" +
                    day1 +
                    "' AND '" +
                    day7 + "'";
            System.out.println(cx);
            rs = stmt.executeQuery(cx);
            rs.beforeFirst();

            //建立输出的哈希表 这里哈希表的格式和之前不太一样了。使用字符串数组了
            HashMap<String, long[]> allmoney = new HashMap<>();
            long pay[] = {0, 0, 0, 0, 0, 0, 0};
            long income[] = {0, 0, 0, 0, 0, 0, 0};
            while (rs.next()) {
                //创建用户信息哈希表

                String date = rs.getString(1).substring(0, 10);
                String method = rs.getString(2);
                Long money = rs.getLong(3);
                //使用一个循环，将所有数据统计到数组 pay 和 income 中
                for (int i = 0; i < 7; i++) {
                    if (date.equals(days[i])) {
                        if (method.equals("pay")) {
                            pay[i] += money;
                        } else {
                            income[i] += money;
                        }
                    }
                }

            }
            rs.close();
            //金额统计数组写入哈希表
            allmoney.put("pay", pay);
            allmoney.put("income", income);

            //把流水统计的三个哈希表写入json
            showJson.add(out);
            showJson.add(allmoney);

            //字符串输出json内容
            response.setStatus(200);
            o.write(showJson.toJSONString());
            //断开数据库连接
            rs.close();
            //使用定义的工具类一键断开con和stmt连接
            //db.closeConnect();
            System.out.println("获取统计数据成功");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            //使用定义的工具类一键断开con和stmt连接
            db.closeConnect();
        }
    }
}
