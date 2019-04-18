package com.yukharin.dao.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcUtils {

    private static final String URL_KEY = "url";

    public static Connection getConnection( String properties_path) {
        Connection connection = null;
        InputStream inputStream = null;
        try {
            Properties properties = new Properties();
            inputStream = new FileInputStream(properties_path);
            properties.load(inputStream);
            connection = DriverManager.getConnection(properties.getProperty(URL_KEY), properties);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return connection;
    }

    public static void closeConnection(Connection connection) {
        Connection temp = connection;
        if (connection != null) {
            try {
                temp.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
