package com.car_rental_system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    static String url = "jdbc:mysql://localhost:3306/car_rental_system";
    static String username = "root";
    static String password = "YOUR_PASSWORD";

    public static Connection getConnection() throws SQLException {

        return DriverManager.getConnection(
                url,
                username,
                password
        );
    }
}