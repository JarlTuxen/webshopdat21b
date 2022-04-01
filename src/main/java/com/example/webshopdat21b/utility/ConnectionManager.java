package com.example.webshopdat21b.utility;

import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    private static String dbConnectionString;
    private static String username;
    private static String password;
    private static Connection connection = null;
    private static Environment environment;



    public static Connection getConnection() {
        //connection er en singleton
        //connection already initialized? Then reuse connection
        if (connection != null) return connection;

        //get environment variables
        dbConnectionString = System.getenv("spring.datasource.url");
        username = System.getenv("spring.datasource.username");
        password = System.getenv("spring.datasource.password");

        //initialize connection
        try {
            connection = DriverManager.getConnection(dbConnectionString, username, password);

        } catch (SQLException e) {
            System.out.println("Could not connect");
            e.printStackTrace();
        }
        return connection;

    }

}
