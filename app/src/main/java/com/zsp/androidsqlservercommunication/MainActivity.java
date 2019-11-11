package com.zsp.androidsqlservercommunication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.zsp.androidsqlservercommunication.library.SqlServerController;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

/**
 * @decs: 主页
 * @author: 郑少鹏
 * @date: 2019/11/8 14:17
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    /**
     * 控件
     */
    private TextView mainActivityTv;
    private Button mainActivityMbConnect;
    private Button mainActivityMbQueryAll;
    private Button mainActivityMbInsert;
    /**
     * SQL Server控制器
     */
    private SqlServerController sqlServerController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 初始化控件
        stepUi();
        // 初始化配置
        initConfiguration();
        // 添监听事件
        setListener();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sqlServerController.close();
    }

    /**
     * 初始化控件
     */
    private void stepUi() {
        mainActivityTv = findViewById(R.id.mainActivityTv);
        mainActivityMbConnect = findViewById(R.id.mainActivityMbConnect);
        mainActivityMbQueryAll = findViewById(R.id.mainActivityMbQueryAll);
        mainActivityMbInsert = findViewById(R.id.mainActivityMbInsert);
    }

    /**
     * 初始化配置
     */
    private void initConfiguration() {
        sqlServerController = new SqlServerController("192.168.1.13", "huiguanjia", "sa", "root");
    }

    /**
     * 添监听事件
     */
    private void setListener() {
        mainActivityMbConnect.setOnClickListener(this);
        mainActivityMbQueryAll.setOnClickListener(this);
        mainActivityMbInsert.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            // 连接
            case R.id.mainActivityMbConnect:
                new ConnectThread().run();
                break;
            // 查询全部
            case R.id.mainActivityMbQueryAll:
                queryAll();
                break;
            // 插入
            case R.id.mainActivityMbInsert:
                insert();
                break;
            default:
                break;
        }
    }

    /**
     * 连接线程
     */
    class ConnectThread implements Runnable {
        @Override
        public void run() {
            Boolean connectState = sqlServerController.connect();
            mainActivityTv.setText(String.format(getString(R.string.connectState), String.valueOf(connectState)));
        }
    }

    /**
     * 查询全部
     */
    private void queryAll() {
        String[] results = new String[4];
        // 查询dbName表所有内容
        String sqlQueryAll = "select * from dbName;";
        ResultSet resultSet = sqlServerController.query(sqlQueryAll);
        // ResultSet最初指向第一行
        while (true) {
            try {
                if (null == resultSet || !resultSet.next()) {
                    break;
                }
                results[0] = resultSet.getString(1);
                results[1] = resultSet.getString(2);
                results[2] = resultSet.getString(3);
                results[3] = resultSet.getString(4);
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (null != resultSet) {
                        resultSet.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        mainActivityTv.setText(Arrays.toString(results));
    }

    /**
     * 插入
     */
    private void insert() {
        String sqlInsert = "insert into dbName(name,password,mail) values('zhangsan','123456','zhangsan@123.com');";
        Boolean insertState = sqlServerController.insert(sqlInsert);
        mainActivityTv.setText(String.format(getString(R.string.insertState), String.valueOf(insertState)));
    }
}
