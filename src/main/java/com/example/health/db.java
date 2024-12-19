package com.example.health;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class db {
    private static final String URL = "jdbc:postgresql://localhost:5432/health_tracker";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }
}

