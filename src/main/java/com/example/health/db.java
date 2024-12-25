package com.example.health;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class db {
    private static final String URL = "jdbc:postgresql:health_tracker";
    private static final String USER = "postgres";
    private static final String PASSWORD = "1234";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}

