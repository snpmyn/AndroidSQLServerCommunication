package controller;

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
    private Connection connection = null;
    private Statement statement = null;
    private String[] results = new String[4];

    /**
     * constructor
     */
    public SqlServerController() {

    }

    /**
     * 连接
     *
     * @return boolean
     */
    public boolean connect() {
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:jtds:sqlserver://" + "192.168.1.13" + ":1433/" + "huiguanjia" + ";charset=utf8", "sa", "root");
            statement = connection.createStatement();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * 查询全部
     *
     * @return boolean
     */
    public String[] queryAll() {
        try {
            // 查询dbName表所有内容
            String sql = "select * from dbName;";
            // ResultSet类似Cursor
            ResultSet resultSet = statement.executeQuery(sql);
            // ResultSet最初指向第一行
            while (resultSet.next()) {
                results[0] = resultSet.getString(1);
                results[1] = resultSet.getString(2);
                results[2] = resultSet.getString(3);
                results[3] = resultSet.getString(4);
            }
            resultSet.close();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return results;
    }

    /**
     * 插入
     *
     * @return boolean
     */
    public boolean insert() {
        String sql = "insert into dbName(name,password,mail) values('zhangsan','123456','zhangsan@123.com');";
        try {
            int executeUpdate = statement.executeUpdate(sql);
            if (executeUpdate > 0) {
                return true;
            }
        } catch (SQLException e) {
            return false;
        }
        return false;
    }
}
