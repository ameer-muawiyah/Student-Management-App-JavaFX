package pk.edu.uet.studentapp;

import pk.edu.uet.studentapp.dao.StudentDAO;
import pk.edu.uet.studentapp.model.Student;

import java.util.List;

public class DatabaseTester {
    public static void main(String[] args) {
        StudentDAO dao = new StudentDAO();

        System.out.println("--- Starting Database Test ---");

        // 1. Test CREATE
        System.out.println("1. Adding a new student...");
        Student testStudent = new Student("Ameer Muawiyah", 20, "Computer Science");
        dao.addStudent(testStudent);
        System.out.println("Student added successfully!");

        // 2. Test READ
        System.out.println("\n2. Fetching all students from MySQL Database:");
        List<Student> allStudents = dao.getAllStudents();

        if (allStudents.isEmpty()) {
            System.out.println("The database is currently empty.");
        } else {
            for (Student s : allStudents) {
                System.out.println(s.toString());
            }
        }

        System.out.println("\n--- Test Complete ---");
    }
}