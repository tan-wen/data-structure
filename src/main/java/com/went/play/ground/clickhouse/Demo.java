package com.went.play.ground.clickhouse;

import com.clickhouse.jdbc.ClickHouseDataSource;

import java.sql.*;
import java.util.Properties;

/**
 * @author White.Wen
 * @version 1.0
 * @date 2022/8/18 17:04
 */
public class Demo {

    public static void main(String[] args) throws SQLException {
        String url = "jdbc:ch://172.20.160.210:8123/demo?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=GMT%2B8";
        Properties properties = new Properties();
        // properties.setProperty("ssl", "true");
        // properties.setProperty("sslmode", "NONE"); // NONE to trust all servers; STRICT for trusted only

        //ClickHouseDataSource dataSource = new ClickHouseDataSource(url, properties);

        try {
            Class.forName("com.clickhouse.jdbc.ClickHouseDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (Connection connection = DriverManager.getConnection(url, properties);
        Statement statement = connection.createStatement();
        //ResultSet resultSet = statement.executeQuery("select * from system.tables limit 10")) {
        ResultSet resultSet = statement.executeQuery("select * from my_first_table limit 10")) {
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int columns = resultSetMetaData.getColumnCount();
            while (resultSet.next()) {
                for (int c = 1; c <= columns; c++) {
                    System.out.print(resultSetMetaData.getColumnName(c) + ":" + resultSet.getString(c) + (c < columns ? ", " : "\n"));
                }
            }
        }
    }
}
