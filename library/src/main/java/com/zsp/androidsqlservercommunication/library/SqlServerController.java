package com.zsp.androidsqlservercommunication.library;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @decs: SQL Server控制器
 * @author: 郑少鹏
 * @date: 2019/11/8 11:33
 */
public class SqlServerController {
    /**
     * IP
     */
    private String ip;
    /**
     * 数据库名
     */
    private String dbName;
    /**
     * 用户
     */
    private String user;
    /**
     * 密码
     */
    private String password;
    /**
     * Statement
     */
    private Statement statement;

    /**
     * constructor
     *
     * @param ip       IP
     * @param dbName   数据库名
     * @param user     用户
     * @param password 密码
     */
    public SqlServerController(String ip, String dbName, String user, String password) {
        this.ip = ip;
        this.dbName = dbName;
        this.user = user;
        this.password = password;
    }

    /**
     * 连接
     *
     * @return boolean
     */
    public boolean connect() {
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            // 连接
            Connection connection = DriverManager.getConnection("jdbc:jtds:sqlserver://" + ip + ":1433/" + dbName + ";charset=utf8", user, password);
            statement = connection.createStatement();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * 查询
     * <p>
     * ResultSet类似Cursor。
     *
     * @param querySql 查询Sql
     * @return 结果集
     */
    public ResultSet query(String querySql) {
        ResultSet resultSet;
        try {
            resultSet = statement.executeQuery(querySql);
            return resultSet;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 插入
     *
     * @param insertSql 插入Sql
     * @return boolean（true成功、false失败）
     */
    public boolean insert(String insertSql) {
        try {
            if (null == statement) {
                return false;
            }
            int executeUpdate = statement.executeUpdate(insertSql);
            if (executeUpdate > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    /**
     * 关闭
     */
    public void close() {
        if (null != statement) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
