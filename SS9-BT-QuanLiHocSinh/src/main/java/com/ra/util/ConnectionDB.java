package com.ra.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
    // ConnectionDB__kết nối CSDL
    private static final String URL = "jdbc:mysql://localhost:3306/ss09_student";
    private static final String USER = "root";
    private static final String PASSWORD = "11111111";

    // phương thức mở kết nối
    public static Connection openConnection() {
        Connection connection = null;
        try {
            // dang ki drive
            Class.forName("com.mysql.cj.jdbc.Driver");
            // lay ket noi
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

//     public static void main(String[] args) {
//     System.out.println(ConnectionDB.openConnection());
//     }

    // phương thức đóng kết nối
    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
