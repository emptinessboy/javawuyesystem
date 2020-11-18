package com.huxiaofan.api;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class dbUtils {
    //瞎写的数据库工具类，方便快速建立连接后获得statement对象，并提供快速断开连接的方法
    Connection con = null;
    Statement stmt = null;

    //获取Statement对象函数
    public Statement getStatement() {
        try {
            con = MySql.getConnection();
            System.out.println("Statement建立成功");
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return stmt;
    }

    public boolean closeConnect() {
        boolean result = false;
        try {
            stmt.close();
            con.close();
            System.out.println("断开Mysql成功");
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }
}
