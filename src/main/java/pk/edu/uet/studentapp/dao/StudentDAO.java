package pk.edu.uet.studentapp.dao;

import pk.edu.uet.studentapp.model.Student;
import pk.edu.uet.studentapp.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    // 1. CREATE: Add a new student to the database
    public void addStudent(Student student) {
        String query = "INSERT INTO students (name, age, major) VALUES (?, ?, ?)";

        // try-with-resources automatically closes the connection
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, student.getName());
            pstmt.setInt(2, student.getAge());
            pstmt.setString(3, student.getMajor());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error adding student: " + e.getMessage());
        }
    }

    // 2. READ: Fetch all students to display in the UI
    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        String query = "SELECT * FROM students";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String major = rs.getString("major");

                students.add(new Student(id, name, age, major));
            }
        } catch (SQLException e) {
            System.err.println("Error fetching students: " + e.getMessage());
        }
        return students;
    }

    // 3. UPDATE: Modify an existing student's record
    public void updateStudent(Student student) {
        String query = "UPDATE students SET name = ?, age = ?, major = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, student.getName());
            pstmt.setInt(2, student.getAge());
            pstmt.setString(3, student.getMajor());
            pstmt.setInt(4, student.getId()); // The WHERE clause

            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error updating student: " + e.getMessage());
        }
    }

    // 4. DELETE: Remove a student from the system completely
    public void deleteStudent(int id) {
        String query = "DELETE FROM students WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error deleting student: " + e.getMessage());
        }
    }
}