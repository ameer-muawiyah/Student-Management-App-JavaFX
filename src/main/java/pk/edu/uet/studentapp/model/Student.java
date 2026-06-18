package pk.edu.uet.studentapp.model;

public class Student {
    private int id;
    private String name;
    private int age;
    private String major;

    // Constructor for retrieving existing students from the database (includes ID)
    public Student(int id, String name, int age, String major) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.major = major;
    }

    // Constructor for inserting new students (ID is omitted because MySQL AUTO_INCREMENT handles it) [cite: 42, 43]
    public Student(String name, int age, String major) {
        this.name = name;
        this.age = age;
        this.major = major;
    }

    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getMajor() { return major; }

    // Overriding toString() so the data formats cleanly inside our JavaFX ListView
    @Override
    public String toString() {
        return "ID: " + id + " | Name: " + name + " | Age: " + age + " | Major: " + major;
    }
}