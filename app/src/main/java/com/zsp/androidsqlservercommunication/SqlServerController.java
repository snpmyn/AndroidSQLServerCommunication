package com.zsp.androidsqlservercommunication;

import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

/**
 * @decs: SQL Server 控制器
 * @author: 郑少鹏
 * @date: 2019/11/8 11:33
 */
public class SqlServerController {
    /**
     * IP
     */
    private final String ip;
    /**
     * 数据库名
     */
    private final String dbName;
    /**
     * 用户
     */
    private final String user;
    /**
     * 密码
     */
    private final String password;
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
     * ResultSet 类似 Cursor
     *
     * @param querySql 查询 SQL
     * @return 结果集
     */
    public ResultSet query(String querySql) {
        ResultSet resultSet;
        try {
            resultSet = statement.executeQuery(querySql);
            return resultSet;
        } catch (Exception e) {
            Log.e(this.getClass().getSimpleName(), Objects.requireNonNull(e.getMessage()));
            return null;
        }
    }

    /**
     * 插入
     *
     * @param insertSql 插入 SQL
     * @return boolean（true 成功、false 失败）
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
            Log.e(this.getClass().getSimpleName(), Objects.requireNonNull(e.getMessage()));
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
                Log.e(this.getClass().getSimpleName(), Objects.requireNonNull(e.getMessage()));
            }
        }
    }
}