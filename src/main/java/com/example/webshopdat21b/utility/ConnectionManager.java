package com.example.webshopdat21b.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    private static Connection connection = null;

    public static Connection getConnection() {
        //connection er en singleton
        //connection already initialized?
        String DB_URL = System.getenv("spring.datasource.url");
        String UID = System.getenv("spring.datasource.username");
        String PWD = System.getenv("spring.datasource.password");

        if (connection != null) return connection;

        //initialize connection
        try {
            connection = DriverManager.getConnection(DB_URL, UID, PWD);

        } catch (SQLException e) {
            System.out.println("Could not connect");
            e.printStackTrace();
        }
        return connection;

    }

    public static Connection getConnectionByValue(String db_url, String uid, String pwd) {
        //connection er en singleton
        //connection already initialized?

        if (connection != null) return connection;

        //initialize connection
        try {
            connection = DriverManager.getConnection(db_url, uid, pwd);

        } catch (SQLException e) {
            System.out.println("Could not connect");
            e.printStackTrace();
        }
        return connection;

    }
}
