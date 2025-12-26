package com.hrs.student.registration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/student_registration";
    private static final String USER = "root"; // Your MySQL username
    private static final String PASS = "Joelget@4"; // Your MySQL password

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        // Load the driver you just added to dependencies
        Class.forName("com.mysql.cj.jdbc.Driver"); 
        return DriverManager.getConnection(URL, USER, PASS);
    }
}