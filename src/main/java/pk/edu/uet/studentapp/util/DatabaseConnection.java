package pk.edu.uet.studentapp.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    // Database connection credentials
    private static final String URL = "jdbc:mysql://localhost:3306/student_management";
    private static final String USER = "root";

    // IMPORTANT: Change this to your actual MySQL Workbench password!
    private static final String PASSWORD = "YOUR_MYSQL_PASSWORD_HERE";

    // Method to grab a connection
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}