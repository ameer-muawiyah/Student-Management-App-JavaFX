package pk.edu.uet.studentapp.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import pk.edu.uet.studentapp.model.Student;

public class StudentController {

    // Linking the FXML elements via their exact fx:id
    @FXML private TextField nameField;
    @FXML private ToggleGroup genderGroup;
    @FXML private ComboBox<String> departmentComboBox;
    @FXML private ListView<Student> studentListView;
    @FXML private Label messageLabel;

    // This list automatically updates the ListView visually when data is added
    private ObservableList<Student> studentList;

    @FXML
    public void initialize() {
        // Initialize the list and bind it to the UI
        studentList = FXCollections.observableArrayList();
        studentListView.setItems(studentList);

        // Populate the dropdown menu
        departmentComboBox.setItems(FXCollections.observableArrayList(
                "Computer Science",
                "Software Engineering",
                "Electrical Engineering",
                "Mechanical Engineering"
        ));
    }

    @FXML
    protected void onSubmit() {
        String name = nameField.getText().trim();
        RadioButton selectedGender = (RadioButton) genderGroup.getSelectedToggle();
        String department = departmentComboBox.getValue();

        // Edge Case Validation: Prevent empty submissions
        if (name.isEmpty() || selectedGender == null || department == null) {
            messageLabel.setText("Validation Error: Please fill in all fields.");
            messageLabel.setTextFill(Color.RED);
            return;
        }

        // Create the Student model and add it to our list
        Student newStudent = new Student(name, selectedGender.getText(), department);
        studentList.add(newStudent);

        // Display success and reset inputs
        messageLabel.setText("Success: Student registered.");
        messageLabel.setTextFill(Color.GREEN);
        resetInputs();
    }

    @FXML
    protected void onClear() {
        resetInputs();
        messageLabel.setText("Form cleared.");
        messageLabel.setTextFill(Color.BLUE);
    }

    // Helper method to keep our code DRY (Don't Repeat Yourself)
    private void resetInputs() {
        nameField.clear();
        if (genderGroup.getSelectedToggle() != null) {
            genderGroup.getSelectedToggle().setSelected(false);
        }
        departmentComboBox.getSelectionModel().clearSelection();
    }
}