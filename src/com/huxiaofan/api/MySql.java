package com.huxiaofan.api;
//导入sql支持
import java.sql.*;

public class MySql {
    // MySQL 8.0 以上版本 - JDBC 驱动名及数据库 URL
    //驱动名字
    static String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static String DB_URL = "jdbc:mysql://localhost:3306/wyglxt?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    // 数据库的用户名与密码，需要根据自己的设置
    static String USER = "hxf_wygl";
    static String PASS = "hxf_wygl";
    //数据库连接对象
    static Connection con = null;

    //以下是静态方法实现，返回一个connection对象
    public static Connection getConnection(){
        //初始化构造函数并建立mysql连接的静态方法
        try {
            Class.forName(JDBC_DRIVER);
            con = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("MySql连接成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }

}
