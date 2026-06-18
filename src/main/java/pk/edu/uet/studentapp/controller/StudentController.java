package pk.edu.uet.studentapp.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import pk.edu.uet.studentapp.dao.StudentDAO;
import pk.edu.uet.studentapp.model.Student;

import java.util.List;

public class StudentController {

    @FXML private TextField nameField;
    @FXML private TextField ageField;   // Replaced the old Gender field
    @FXML private TextField majorField; // Replaced the old Department field
    @FXML private Label messageLabel;
    @FXML private ListView<Student> studentListView;

    // Connect our Database Access Object
    private StudentDAO studentDAO = new StudentDAO();
    private ObservableList<Student> studentObservableList;

    @FXML
    public void initialize() {
        // Automatically fetch data from MySQL when the app launches!
        loadStudentsFromDatabase();
    }

    @FXML
    protected void onSubmit() {
        String name = nameField.getText();
        String ageText = ageField.getText();
        String major = majorField.getText();

        // Basic validation
        if (name.isEmpty() || ageText.isEmpty() || major.isEmpty()) {
            messageLabel.setText("Error: All fields are required!");
            messageLabel.setTextFill(Color.RED);
            return;
        }

        try {
            // Convert the age text into a numeric integer for the database
            int age = Integer.parseInt(ageText);

            // 1. Create the model
            Student newStudent = new Student(name, age, major);

            // 2. Save directly to MySQL Database!
            studentDAO.addStudent(newStudent);

            // 3. Update the UI
            messageLabel.setText("Success: Student saved to database!");
            messageLabel.setTextFill(Color.GREEN);
            resetInputs();

            // Refresh the ListView to show the newly added student
            loadStudentsFromDatabase();

        } catch (NumberFormatException e) {
            // Catch the error if someone types "Twenty" instead of "20"
            messageLabel.setText("Error: Age must be a number!");
            messageLabel.setTextFill(Color.RED);
        }
    }

    @FXML
    protected void onClear() {
        resetInputs();
        messageLabel.setText("");
    }

    private void resetInputs() {
        nameField.clear();
        ageField.clear();
        majorField.clear();
    }

    private void loadStudentsFromDatabase() {
        // Fetch the latest list directly from MySQL and bind it to the JavaFX ListView
        List<Student> dbStudents = studentDAO.getAllStudents();
        studentObservableList = FXCollections.observableArrayList(dbStudents);
        studentListView.setItems(studentObservableList);
    }
}