package pk.edu.uet.studentapp.model;

public class Student {
    private String name;
    private String gender;
    private String department;

    // Constructor
    public Student(String name, String gender, String department) {
        this.name = name;
        this.gender = gender;
        this.department = department;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    // This override ensures the ListView displays the exact string format requested in the lab
    @Override
    public String toString() {
        return "Name: " + name + ", Gender: " + gender + ", Course: " + department;
    }
}