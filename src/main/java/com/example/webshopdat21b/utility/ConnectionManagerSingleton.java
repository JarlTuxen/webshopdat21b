package com.example.webshopdat21b.utility;

import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManagerSingleton implements EnvironmentAware {

    private static String dbConnectionString;
    private static String username;
    private static String password;
    private static Connection connection = null;
    private Environment environment;

    public static Connection getConnection() {

        //connection already initialized? Then reuse connection
        if (connection != null) return connection;

        //get environment variables - environment can't be accessed from static context
        //dbConnectionString = environment.getProperty("spring.datasource.url");
        //username = environment.getProperty("spring.datasource.username");
        //password = environment.getProperty("spring.datasource.password");

        //initialize connection
        try {
            connection = DriverManager.getConnection(dbConnectionString, username, password);

        } catch (SQLException e) {
            System.out.println("Could not connect");
            e.printStackTrace();
        }
        return connection;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
